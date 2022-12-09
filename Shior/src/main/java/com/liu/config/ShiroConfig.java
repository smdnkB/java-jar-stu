package com.liu.config;

import com.liu.shiroRealm.AuthRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {



    //

    // shiro过滤器
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 给过滤器设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 配置公共资源
        Map<String,String> map = new HashMap<>();
        map.put("/index","authc");// authc 需要认证和授权  anon 不做验证授权

        // 配置默认到达页面
        shiroFilterFactoryBean.setLoginUrl("login");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    // shiro权限管理器
    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") Realm realm,@Qualifier("defaultWebSessionManager")DefaultWebSessionManager defaultWebSessionManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 管理realm
        defaultWebSecurityManager.setRealm(realm);
        // 管理会话
        defaultWebSecurityManager.setSessionManager(defaultWebSessionManager);

        return defaultWebSecurityManager;
    }

    // 自定义realm
    @Bean("realm")
    public Realm getRealm(){
        AuthRealm authRealm = new AuthRealm();
        // 修改密码验证匹配器
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//        matcher.setHashAlgorithmName("MD5");
//        matcher.setHashIterations(1024);
//        authRealm.setCredentialsMatcher(matcher);

        // 开启缓存管理(本地缓存)
//        authRealm.setCacheManager(new EhCacheManager());
        authRealm.setCachingEnabled(true);
        authRealm.setAuthenticationCachingEnabled(true);// 认证缓存
        authRealm.setAuthorizationCachingEnabled(true); // 授权缓存
        authRealm.setAuthenticationCacheName("Authen");
        authRealm.setAuthorizationCacheName("Author");

        return authRealm;
    }

    // 会话管理器
    @Bean("defaultWebSessionManager")
    public DefaultWebSessionManager defaultWebSessionManager(@Qualifier("simpleCookie")SimpleCookie simpleCookie){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionIdCookieEnabled(true);//使cookie生效
        defaultWebSessionManager.setSessionIdCookie(simpleCookie);// 指定cookie生成策略
        defaultWebSessionManager.setGlobalSessionTimeout(60*60*24*7);// 指定会话过期时间
//        defaultWebSessionManager.setSessionDAO(); 默认session存本地  重写方法可以存redis
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(false);// 关闭会话更新

        return defaultWebSessionManager;
    }

    // cookie管理器
    @Bean("simpleCookie")
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("simpleCookie");
        return simpleCookie;
    }


    // 生命周期管理器

}
