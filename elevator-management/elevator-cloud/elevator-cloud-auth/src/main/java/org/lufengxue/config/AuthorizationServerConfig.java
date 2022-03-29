package org.lufengxue.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.security.KeyPair;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue.config
 * 日    期:  2022-03-2022/3/29
 * 时    间:  11:14
 * 描    述:
 */

/**
 * 标识该微服务就是一个认证服务器
 */
@Configuration
@EnableAspectJAutoProxy
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 该配置用于配置开放端点使用，比如哪些安全端点需要放行(加密密码)
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    //用于配置数据库
    @Autowired
    private ClientDetailsService jdbcClientDetailsService;
    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Autowired
    private JKSProperties jksProperties;

    /**
     * 授权服务器安全配置程序
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //默认为 denyAll 表示拒绝所有  这里开放端点为申请令牌的端点 应当为所有人都可以访问
                .tokenKeyAccess("permitAll()")
                //默认也是为denyAll 表示拒绝所有  校验 令牌端点 应当是 只有登录之后才能校验
                .checkTokenAccess("isAuthenticated()")
                //设置密码需要使用加密器 针对客户端
                .passwordEncoder(passwordEncoder);

    }

    /**
     * 客户端详细信息服务配置器
     * 设置客户端配置 一定要配置，不配置就会报错 标识客户端配置项 ，
     * 支持 哪些授权模式 这里指定为xxx客户端可以有哪些授权模式
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //数据库方式配置客户端
        clients.jdbc(dataSource).clients(jdbcClientDetailsService);
    }

    /**
     * 设置数据库的方式的客户端
     */
    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }
    /**
     * jwt 访问 token 转换器
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        //设置jwt的转换器对象
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        //设置jwt的秘钥库 名字，密码，获取秘钥对的密码，别名
        String keystorepath =jksProperties.getName();
        String storepassword = jksProperties.getStorepassword();
        String keypassword = jksProperties.getKeypassword();
        String alias = jksProperties.getAlias();


        KeyPair keyPair = new KeyStoreKeyFactory(
                //设置加密的加载文件
                new ClassPathResource(keystorepath),
                //设置加密的加载文件
                storepassword.toCharArray())
                //设置读取秘钥库文件的密码,设置获取秘钥的密码
                .getKeyPair(alias, keypassword.toCharArray());
        //设置秘钥对象
        converter.setKeyPair(keyPair);

        //使用JWT的令牌转换器
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        //设置用户转换器
        //accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        return converter;
    }
}

