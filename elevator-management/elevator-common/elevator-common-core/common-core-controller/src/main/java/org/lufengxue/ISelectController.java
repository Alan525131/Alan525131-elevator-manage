package org.lufengxue;

import org.lufengxue.response.Result;

import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 日    期:  2022-03-2022/3/31
 * 时    间:  13:36
 * 描    述:
 */
public interface ISelectController<T> {
    //根据ID 获取信息
    public Result<T> findById(Object id);


    //根据ID 获取信息列表
    public Result<List<T>> findAll();
}
