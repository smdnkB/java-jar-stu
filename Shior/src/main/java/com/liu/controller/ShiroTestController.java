package com.liu.controller;

import com.liu.MyToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroTestController {

    @RequestMapping("/index")
    public void index(){
        System.out.println("====index=====");
    }

    @RequestMapping("/login")
    public void login(){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken("liu","123456"));
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }


        System.out.println("====login=====");
    }

    @RequestMapping("/logout")
    public void logout(){
        System.out.println("====logout=====");

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }



    }
}
