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
                .setExpiration(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 3))
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

    public static void main(String[] args) {
//        Claims parse = parse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NzQzMjA0MzcsImV4cCI6MTU3MjYxNzQ3MCwidWlkIjoiMTUiLCJpc3MiOiJsd3MifQ.5fXzJlW_o1Kr5J81-5HExjuQlp8M3lHphp_7GTcXsOs");
//        System.out.println(parse);
        User user = new User();
        user.setUid(111);
        user.setName("1333");
        String token = creatToken(user, new Date());
        System.out.println(token);
        Claims parse = parse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NzQzMjg3NDgsImV4cCI6MTU3MjYyNTc4MCwidWlkIjoiMTkiLCJpc3MiOiJsd3MifQ.K35zbOGqBiuhgVfX5NPvxhd26qwO5Essyu-LN6-yFC0");
        System.out.println(parse);
    }
}
