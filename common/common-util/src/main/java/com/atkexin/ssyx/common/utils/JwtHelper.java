package com.atkexin.ssyx.common.utils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JwtHelper {
    private static long tokenExpiration = 365*24*60*60*1000;//过期时间
    private static String tokenSignKey = "ssyx";//密钥
//生成token
    public static String createToken(Long userId, String userName) {
        String token = Jwts.builder()
                .setSubject("ssyx-USER")//分组
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))//过期时间//当前时间+
                .claim("userId", userId)//私有部分
                .claim("userName", userName)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)//密钥
                .compressWith(CompressionCodecs.GZIP)//压缩
                .compact();//生成
        return token;
    }

    //得到UserId//验证
    public static Long getUserId(String token) {
        if(com.alibaba.nacos.api.utils.StringUtils.isEmpty(token)) return null;

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);//密钥解密
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");//根据字段得到
        return userId.longValue();
        // return 1L;
    }
//得到Username//验证
    public static String getUserName(String token) {
        if(StringUtils.isEmpty(token)) return "";

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userName");
    }

    public static void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

}
