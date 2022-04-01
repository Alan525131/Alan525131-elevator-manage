package org.lufengxue.user.controller;


import lombok.extern.slf4j.Slf4j;
import org.lufengxue.enums.StatusCode;
import org.lufengxue.response.Result;
import org.lufengxue.user.pojo.dto.UserDto;
import org.lufengxue.user.pojo.po.UserPo;
import org.lufengxue.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue.controller
 * 日    期:  2022-03-2022/3/30
 * 时    间:  0:37
 * 描    述:
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserControler {


    @Autowired
    private UserService userService;

    /**
     *  新增用户  todo sql 问题
     * @param
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody UserPo userPo) {
        Integer userNumber = userService.insert(userPo);
        if (userNumber >= 1) {
            return new Result(true, StatusCode.OK, "添加数据成功");
        } else {
            return new Result(false, StatusCode.ERROR, "添加数据失败");

        }
    }
    /**
     * feign调用  根据用户名 查询用户信息
     */
    @GetMapping("/load")
    public Result<UserDto> findByName(@RequestParam(name = "name") String name){
        UserDto user = userService.findByName(name);
        return new Result(true, StatusCode.OK,"查询用户数据成功",user);
    }
    /**
     * 删除用户
     */
    @PostMapping("/delete/{id}")
    public Result deleteId(@PathVariable(name = "id") Integer id){
        Integer number = userService.deleteId(id);
        if(number >= 1){
            return new Result<>(true,StatusCode.OK,"删除数据成功");
        }else {
            return new Result<>(false,StatusCode.ERROR,"删除用户数据失败");
        }
    }

    /**
     * 查询用户列表
     * @return
     */
    @GetMapping
    public Result<List<UserDto>> findAll(){
       List<UserDto> userDtoList = userService.findAll();
       return  new Result<>(true,StatusCode.OK,"查询用户数据成功",userDtoList);
    }
    /**
     * 更新数据
     */
    @PostMapping
    public Result updateUser(@RequestBody UserDto userDto){
      Integer number = userService.updateUser(userDto);
      if(number >0){
          return new Result(true,StatusCode.OK,"更新用户数据成功");
      }else {
          throw new RuntimeException("更新用户数据失败");
      }
    }

}
