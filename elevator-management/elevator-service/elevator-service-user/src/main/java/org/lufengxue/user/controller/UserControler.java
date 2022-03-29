package org.lufengxue.user.controller;

import org.lufengxue.user.enums.StatusCode;
import org.lufengxue.user.pojo.bo.Result;
import org.lufengxue.user.pojo.po.User;
import org.lufengxue.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue.controller
 * 日    期:  2022-03-2022/3/30
 * 时    间:  0:37
 * 描    述:
 */
@RestController
@RequestMapping("/user")
public class UserControler {


    @Autowired
    private UserService userService;

    /**
     * c查询用户信息
     */
    @GetMapping("/load")
    public Result<User>findByName(String name){
        User user = userService.findByName(name);
        return new Result<>(true, StatusCode.OK,"查询用户数据成功",user);
    }
}
