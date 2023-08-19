package com.liu;

import org.apache.shiro.authc.AuthenticationToken;

public class MyToken implements AuthenticationToken {

    private String userName;
    private String password;

    public MyToken(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Object getPrincipal() {
        return this.userName;
    }
    // 密码比较时会对此处返回的密码进行hash，然后与数据库已存在的密文比较
    @Override
    public Object getCredentials() {
        return this.password; // 前端密码参数
    }
}
