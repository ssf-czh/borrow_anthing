package com.czh.common.utils;

import com.czh.pojo.User;
import io.jsonwebtoken.*;

import java.util.Date;

public class JWTUtil {

    private static String key = "ccczzzhhh";

    //生成Token信息方法（根据有效的用户信息）
    public static String creatToken(User user, Date date) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT") // 设置header
                .setHeaderParam("alg", "HS256").setIssuedAt(date) // 设置签发时间
                .setExpiration(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 30))
                .claim("uid",String.valueOf(user.getUid()) ) // 设置内容
                .setIssuer("lws")// 设置签发人
                .signWith(signatureAlgorithm, key); // 签名，需要算法和key
        String jwt = builder.compact();
        return jwt;
    }

    public static Claims parse(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    public static Integer parseToUid(String token){

         String suid = (String) Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().get("uid");
         return Integer.parseInt(suid);
    }
}
