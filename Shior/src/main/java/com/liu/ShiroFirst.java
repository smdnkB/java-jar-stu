package com.liu;

import com.liu.shiroRealm.AuthRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * 单应用 shiro
 */
public class ShiroFirst {
    public static void main(String[] args) {
        // 创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        // 给安全管理器设置realm
//        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        securityManager.setRealm(new AuthRealm());

        // 给全局安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();

//        UsernamePasswordToken token = new UsernamePasswordToken("root", "root");
        AuthenticationToken token = new MyToken("admin","password");

        try {
            subject.login(token); // 登录认证  登录失败会抛出异常
            boolean authenticated = subject.isAuthenticated(); // 当前用户的认证状态
            System.out.println(authenticated);

            // 验证授权
            System.out.println(subject.hasRole("add"));
            System.out.println(subject.isPermitted("a"));
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

    }
}
