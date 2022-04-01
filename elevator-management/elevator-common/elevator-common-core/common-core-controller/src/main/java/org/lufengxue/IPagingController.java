package org.lufengxue;
import com.github.pagehelper.PageInfo;
import org.lufengxue.response.Result;
/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 日    期:  2022-03-2022/3/31
 * 时    间:  13:38
 * 描    述:
 */
public interface IPagingController<T> {
    /**
     * 查询所有并分页
     *
     * @param pageNo 当前页码
     * @param pageSize 页码大小
     * @return
     */
      Result<PageInfo<T>> findByPage(Integer pageNo, Integer pageSize);
    /**
     * 根据查询条件 record 分页查询
     *
     * @param pageNo    当前页码
     * @param pageSize  页码大小
     * @param record    查询条件
     * @return
     */
    Result<PageInfo<T>> findByPage(Integer pageNo, Integer pageSize, T record);



}
