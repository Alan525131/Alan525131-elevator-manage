package org.lufengxue.core.service;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 日    期:  2022-03-2022/3/31
 * 时    间:  13:38
 * 描    述:
 */
public interface CoreService<T> extends
        DeleteService<T>,
        org.lufengxue.core.service.InsertService<T>,
        org.lufengxue.core.service.PagingService<T>,
        org.lufengxue.core.service.SelectService<T>,
        org.lufengxue.core.service.UpdateService<T> {

}
