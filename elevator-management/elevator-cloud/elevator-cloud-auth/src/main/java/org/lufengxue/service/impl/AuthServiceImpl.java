package org.lufengxue.service.impl;

import org.lufengxue.user.pojo.bo.AuthToken;
import org.lufengxue.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue.service.impl
 * 日    期:  2022-03-2022/3/29
 * 时    间:  20:34
 * 描    述:
 */
public class AuthServiceImpl implements AuthService {

    // Ribbon 调用
    @Autowired
    private LoadBalancerClient loabBalancerClient;

    @Autowired
    private RestTemplate restTemplate;
    /****
     * 申请令牌
     */
    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret) {
        //申请令牌
        AuthToken authToken = applyToken(username,password,clientId, clientSecret);
        if(authToken == null){
            throw new RuntimeException("申请令牌失败");
        }
        return authToken;
    }

    /****
     * 认证方法
     * @param username:用户登录名字
     * @param password：用户密码
     * @param clientId：配置文件中的客户端ID
     * @param clientSecret：配置文件中的秘钥
     * @return
     */
    private AuthToken applyToken(String username, String password, String clientId, String clientSecret) {
        //选中认证服务的地址
        ServiceInstance serviceInstance = loabBalancerClient.choose("oauth");
        if(serviceInstance == null){
            throw new RuntimeException("找不到服务");
        }
        //获取令牌的url
        String path = serviceInstance.getUri().toString() + "/oauth/token";
        //定义body
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        //授权方式
        formData.add("grant_type", "password");
        //账号
        formData.add("username", username);
        //密码
        formData.add("password", password);
        //定义头
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        //base64算法
        header.add("Authorization", httpbasic(clientId, clientSecret));
        //指定 restTemplate当遇到400或401响应时候也不要抛出异常，也要正常返回值
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            /**
             * 重写 错误响应方法
             * @param response
             * @throws IOException
             */
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                //当响应的值为400或401时候也要正常响应，不要抛出异常
                if(response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401){
                    super.handleError(response);
                }
            }
        });

        Map map = null;
        try {
            //http请求spring security的申请令牌接口
            ResponseEntity<Map> mapResponseEntity = restTemplate.exchange(path, HttpMethod.POST, new HttpEntity<>(formData, header), Map.class);
            map = mapResponseEntity.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //如果从map中请求体中缺少数据 证明失败
        if(map == null || map.get("access_token") == null || map.get("refresh_token") == null || map.get("jti") == null) {
            //jti是jwt令牌的唯一标识作为用户身份令牌
            throw new RuntimeException("创建令牌失败！");
        }

        //将响应数据封装成AuthToken对象
        AuthToken authToken = new AuthToken();
        //访问令牌(jwt)
        String accessToken = (String) map.get("access_token");
        //刷新令牌(jwt)
        String refreshToken = (String) map.get("refresh_token");
        //jti，作为用户的身份标识
        String jwtToken= (String) map.get("jti");
        authToken.setJti(jwtToken);
        authToken.setAccessToken(accessToken);
        authToken.setRefreshToken(refreshToken);
        return authToken;
    }
    /***
     * base64编码
     * @param clientId
     * @param clientSecret
     * @return
     */
    private String httpbasic(String clientId, String clientSecret) {
        //将客户端id和客户端密码拼接，按“客户端id:客户端密码”
        String string = clientId+":"+clientSecret;
        //进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic "+new String(encode);
    }
}
