package org.lufengxue;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.lufengxue.core.service.CoreService;
import org.lufengxue.enums.StatusCode;
import org.lufengxue.response.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequestInterceptor;

import java.util.List;
import java.util.Map;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 日    期:  2022-03-2022/3/31
 * 时    间:  13:52
 * 描    述:
 */
public abstract class AbstractCoreController<T> implements ICoreController<T>, WebRequestInterceptor {
    //调用方的service
    protected CoreService<T> coreService;
    //调用方的类型
    protected Class<T> clazz;

    protected Map<String, String> map;

    public AbstractCoreController(CoreService<T> coreService, Class<T> clazz) {
        this.coreService = coreService;
        this.clazz = clazz;
    }
    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Override
    public Result deleteById(@PathVariable(name = "id") Object id) {
        coreService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 添加记录
     *
     * @param record
     * @return
     */
    @PostMapping
    @Override
    public Result insert(@RequestBody T record) {
        coreService.insert(record);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 分页查询记录
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    @Override
    public Result<PageInfo<T>> findByPage(@PathVariable(name = "page") Integer pageNo,
                                          @PathVariable(name = "size") Integer pageSize) {
        PageInfo<T> pageInfo = coreService.findByPage(pageNo, pageSize);
        return new Result<PageInfo<T>>(true, StatusCode.OK, "分页查询成功", pageInfo);
    }

    /**
     *
     * @param pageNo    当前页码
     * @param pageSize  页码大小
     * @param record    查询条件
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    @Override
    public Result<PageInfo<T>> findByPage(@PathVariable(name = "page") Integer pageNo,
                                          @PathVariable(name = "size") Integer pageSize,
                                          @RequestBody T record) {
        PageInfo<T> pageInfo = coreService.findByPage(pageNo, pageSize, record);
        return new Result<PageInfo<T>>(true, StatusCode.OK, "条件分页查询成功", pageInfo);
    }

    /**
     *  查询单个数据
     * @param id
     * @return
     */
    @Override
    @GetMapping("/{id}")
    public Result<T> findById(@PathVariable(name = "id") Object id) {
        T t = coreService.selectByPrimaryKey(id);
        return new Result<T>(true, StatusCode.OK, "查询单个数据成功", t);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    @GetMapping
    public Result<List<T>> findAll() {
        List<T> list = coreService.selectAll();
        return new Result<List<T>>(true, StatusCode.OK, "查询所有数据成功", list);
    }

    /**
     *  //更新数据
     * @param record
     * @return
     */
    @Override
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody T record) {
        coreService.updateByPrimaryKey(record);
        return new Result(true, StatusCode.OK, "更新成功");
    }
}
