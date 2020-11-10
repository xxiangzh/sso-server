package com.xzh.sso.utils;

import com.xzh.sso.common.SecurityConstants;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Token工具
 *
 * @author 向振华
 * @date 2020/09/08 09:48
 */
@Slf4j
public class TokenUtils {

    /**
     * 获取claims
     *
     * @param token
     * @return
     */
    public static Claims getClaims(String token) {
        Claims claims = null;
        try {
            //解析claims
            claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.JWT_SIGNING.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("JWT过期：", e);
        } catch (SignatureException e) {
            log.error("JWT签名错误：", e);
        } catch (MalformedJwtException e) {
            log.error("JWT格式错误：", e);
        } catch (Exception e) {
            log.error("JWT解析异常：", e);
        }
        return claims;
    }
}