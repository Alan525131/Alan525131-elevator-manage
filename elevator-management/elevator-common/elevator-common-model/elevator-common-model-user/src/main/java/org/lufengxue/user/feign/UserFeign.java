package org.lufengxue.user.feign;

import org.lufengxue.user.pojo.bo.Result;
import org.lufengxue.user.pojo.po.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue.feign
 * 日    期:  2022-03-2022/3/30
 * 时    间:  1:30
 * 描    述:
 */
@FeignClient(name = "user",path = "/user")
public interface UserFeign {
    /**
     * c查询用户信息
     */
    @GetMapping("/load")
    public Result<User> findByName(String name);
}
