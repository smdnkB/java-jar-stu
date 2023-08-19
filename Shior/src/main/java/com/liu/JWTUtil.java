package com.liu;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    /**
     * 生成 token 令牌
     * @param iss 签发者
     * @param ttlMillis 过期时间
     * @param sessionId 唯一id
     * @param claim 非隐私信息
     * @return
     */
    public String issuedToken(String iss, long ttlMillis, String sessionId, Map<String,Object> claim){

        if (claim == null) claim = new HashMap<>();

        String base64EncodedSecretKey = BnCodeUtils.encodeBase64("key".getBytes(StandardCharsets.UTF_8));
        JwtBuilder builder = Jwts.builder()
                .setClaims(claim) // 构建非隐私型数据
                .setId(sessionId) // 构建唯一标识 使用sessionID
                .setIssuedAt(new Date(System.currentTimeMillis())) // 构建签发时间
                .setSubject(iss) // 签发者
                .signWith(SignatureAlgorithm.HS256,base64EncodedSecretKey);
        // 如果指定了过期时间 设置过期时间
        if (ttlMillis>0) builder.setExpiration(new Date(ttlMillis+System.currentTimeMillis())); // 指定算法和密钥

        return builder.compact();
    }

    /**
     * 解析 token
     * @param jwtToken token参数
     * @return
     */
    public Claims decodeToken(String jwtToken){
        String base64EncodedSecretKey = BnCodeUtils.encodeBase64("key".getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .setSigningKey(base64EncodedSecretKey)
                .parseClaimsJws(jwtToken)
                .getBody();
    }


    public boolean isVerifyToken(String jwtToken){
        try {
            Algorithm algorithm = Algorithm.HMAC256("key".getBytes(StandardCharsets.UTF_8));
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(jwtToken);
        } catch (IllegalArgumentException e) {
            System.out.print("token 验证失败");
            e.printStackTrace();
        }
        return true;
    }

}
