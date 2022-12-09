package com.liu.controller;

import com.liu.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
@Api(value = "广告管理", description = "提供对广告对象的增删改查")
public class TestSwaggerController {

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public String test(){
        return "hello word";
    }


    @ApiOperation("获取用户信息接口")
    @PostMapping("/getUser")
    public User getUser(){
        return new User();
    }

    @ApiOperation(value = "添加用户信息接口",notes = "根据用户名和用户信息")
    @PostMapping("/addUser")
    public User addUser(@ApiParam("用户名")String name, @ApiParam("年龄")Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }
}
