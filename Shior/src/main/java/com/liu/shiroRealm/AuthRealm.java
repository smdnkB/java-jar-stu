package com.liu.shiroRealm;

import com.liu.DigestsUtil;
import com.liu.MyToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.MapCache;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AuthRealm extends AuthorizingRealm {

    // 修改密码比较方式 和系统加密方式保持一致
    public AuthRealm(){
        // 指定加密方式
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher(DigestsUtil.SHA1);
        // 指定密码迭代次数
        hashedCredentialsMatcher.setHashIterations(DigestsUtil.iterator);
        // 使父方法的匹配方式生效
        setCredentialsMatcher(hashedCredentialsMatcher);
        // 修改使用自定义token
        this.setAuthenticationTokenClass(MyToken.class);

        // 开启缓存管理(本地缓存)  如果realm内部自定义了缓存，这里注释掉
//        this.setCacheManager(new EhCacheManager()); // 设置使用的缓存管理器。自定义缓存的话这里自动失效
        this.setCachingEnabled(true);
        this.setAuthenticationCachingEnabled(true);// 认证缓存
        this.setAuthorizationCachingEnabled(true); // 授权缓存
        this.setAuthenticationCacheName("Authen");
        this.setAuthorizationCacheName("Author");
        // 设置认证和授权的缓存。自定义缓存的话实现 CaChe接口
        this.setAuthenticationCache(new RealmCache<>()); // 认证缓存
        this.setAuthorizationCache(new RealmCache<>()); // 授权缓存
    }


    /**
     *  认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal(); // 获取用户名

        // 使用用户名去数据库查询密码（密文）
        String password = "10f833366391e6d10bd4d3e217172f0e9bc177f8";

        // 散列密码比较(这里的密码参数是从数据库获取的密文)
        return new SimpleAuthenticationInfo(principal,password,DigestsUtil.salt,getName());
    }

    /**
     *  授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal(); // 身份信息:用户名

        // 通过用户名查数据库 获取用户的权限列表
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(Arrays.asList("add","del"));
        authorizationInfo.addStringPermissions(Arrays.asList("a","b"));

        return authorizationInfo;
    }

}
