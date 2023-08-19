package com.liu;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import javax.sql.rowset.spi.SyncResolver;
import java.util.HashMap;

/**
 * 散列算法加密
 */
public class DigestsUtil {

    public static final String SHA1 = "SHA-1"; // 加密算法
    public static final ByteSource salt = ByteSource.Util.bytes("salt"); // 盐
    public static final Integer iterator = 512; // 加密次数


    /**
     * sha-1 加密
     * @param input 输入
     * @param salt  盐
     * @param iterator  加密次数
     * @return  输出
     */
    public static String sha1(String input, ByteSource salt, Integer iterator){
        return new SimpleHash(SHA1,input,salt ,iterator).toString();
//        return new SimpleHash(SHA1,input,salt,iterator).getBytes();
    }

    /**
     * 生成随机盐
     * @return 以十六进制字符串返回
     */
    public static String generateSalt(){
        SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
        return generator.nextBytes().toHex();
    }

    public static void main(String[] args) {
        System.out.println(sha1("password",salt,512));
//        byte[] admins = sha1("admin", salt, iterator);
//        System.out.print(admin);
//        storedBytes = Hex.decode(storedBytes);
    }
}
