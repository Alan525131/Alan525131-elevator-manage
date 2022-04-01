package org.lufengxue.user.service.impl;

import org.lufengxue.response.Result;
import org.lufengxue.user.mapper.UserMapper;
import org.lufengxue.user.pojo.dto.UserDto;
import org.lufengxue.user.pojo.po.UserPo;
import org.lufengxue.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue.user.service.impl
 * 日    期:  2022-03-2022/3/31
 * 时    间:  14:58
 * 描    述:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 根据用户名查询数据
     * @param name
     * @return
     */
    @Override
    public UserDto findByName(String name) {
        return userMapper.findByName(name);
    }

    /**
     * 新增用户
     * @param userPo
     */
    @Override
    public Integer insert(UserPo userPo) {
        return userMapper.insert(userPo);
    }

    /**
     * 根据用户 id删除用户数据
     * @param id
     * @return
     */
    @Override
    public Integer deleteId(Integer id) {
        return userMapper.deleteId(id);
    }

    /**
     * 查询用户列表
     * @return
     */
    @Override
    public List<UserDto> findAll() {
        return userMapper.findAll();
    }

    /**
     * 更新数据
     * @param userPo
     * @return
     */
    @Override
    public Integer updateUser(UserPo userPo) {
        return userMapper.updateUser(userPo);
    }

}
