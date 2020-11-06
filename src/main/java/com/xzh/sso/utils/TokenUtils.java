package com.xzh.sso.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * Token工具
 *
 * @author 向振华
 * @date 2020/09/08 09:48
 */
@Slf4j
public class TokenUtils {

    public static String jwtSigningKey = "jwtSigningKey";

    /**
     * 获取claims
     *
     * @param token
     * @return
     */
    public static Claims getClaims(String token) {
        Claims claims;
        try {
            //解析claims
            claims = Jwts.parser()
                    .setSigningKey(jwtSigningKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("JWT过期：", e);
            return null;
        }catch (MalformedJwtException e) {
            log.error("JWT格式错误：", e);
            return null;
        }catch (Exception e) {
            log.error("JWT解析异常：", e);
            return null;
        }
        return claims;
    }
}