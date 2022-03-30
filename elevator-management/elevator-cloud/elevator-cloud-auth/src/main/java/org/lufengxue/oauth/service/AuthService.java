package org.lufengxue.oauth.service;

import org.lufengxue.user.pojo.bo.AuthToken;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue.service
 * 日    期:  2022-03-2022/3/29
 * 时    间:  20:30
 * 描    述:
 */
public interface AuthService {
    /***
     * 授权认证方法
     */
    AuthToken login(String username, String password, String clientId, String clientSecret);
}
