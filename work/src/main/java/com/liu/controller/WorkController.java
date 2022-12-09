package com.liu.controller;

import com.liu.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class WorkController {

    @RequestMapping("/index")
    public String index(){
        System.out.println("请求返回首页");
        return "index";
    }


    @ResponseBody
    @RequestMapping("/getData")
    public String getData(User user){
        System.out.println(user);
        System.out.println("处理普通数据");
        return "ok data";
    }

    @ResponseBody
    @RequestMapping("/getJson")
    public String getJson(@RequestBody User user){
        System.out.println(user);
        System.out.println("处理Json数据");
        return "ok json";
    }

    @ResponseBody
    @RequestMapping("/getMap")
    public String getMap(@RequestBody Map map){
        System.out.println(map);
        System.out.println("Json数据--> map");
        return "ok json to map";
    }

    @ResponseBody
    @RequestMapping("/getDataforMap")
    public String getDataforMap(Map map){
        System.out.println(map);
        System.out.println("Json数据--> map");
        return "ok data to map";
    }

}
