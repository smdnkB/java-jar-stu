package com.liu;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

import java.nio.charset.StandardCharsets;

/**
 * shiro 内置hex和base64加解密工具 加密可逆 -- 非散列算法
 * 散列算法是一种不可逆的算法包括 md5  sha；使用散列算法需要加盐，因为散列算法密文固定；适合使用在密码加密
 */
public class BnCodeUtils {

    public static void main(String[] args) {
        String val = "12345yyy";
        // 加密
        String encodeHex = BnCodeUtils.encodeHex(val.getBytes());
        // 解密
        String s = new String(BnCodeUtils.decodeHex(encodeHex));
        // 原文和密文对比  true
        System.out.print(val.equals(s));
    }

    /**
     * 加密
     * @param input
     * @return
     */
    public static String encodeHex(byte[] input){
        return Hex.encodeToString(input);
    }
    public static String encodeBase64(byte[] input){
        return Base64.encodeToString(input);
    }

    /**
     * 解密
     * @param input
     * @return
     */
    public static byte[] decodeHex(String input){
        return Hex.decode(input);
    }
    public static byte[] decodeBase64(String input){
        return Base64.decode(input);
    }
}
