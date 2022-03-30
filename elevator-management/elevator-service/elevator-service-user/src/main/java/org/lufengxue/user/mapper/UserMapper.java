package org.lufengxue.user.mapper;

import org.lufengxue.user.pojo.po.UserPo;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue.user.mapper
 * 日    期:  2022-03-2022/3/30
 * 时    间:  1:11
 * 描    述:
 */
public interface UserMapper {

    // todo   有问题待解决 明天再来

    UserPo findByName(String name);
}
