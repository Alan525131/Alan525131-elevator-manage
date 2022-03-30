package org.lufengxue.user.service.impl;

import org.lufengxue.user.pojo.po.UserPo;
import org.lufengxue.user.mapper.UserMapper;
import org.lufengxue.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue.user.service.impl
 * 日    期:  2022-03-2022/3/30
 * 时    间:  1:08
 * 描    述:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserPo findByName(String name) {
        return userMapper.findByName(name);
    }
}
