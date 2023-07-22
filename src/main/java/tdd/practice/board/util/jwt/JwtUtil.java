package tdd.practice.board.util.jwt;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    //JWT 생성 메서드
    public static String createJwt(String email, String secretKey, Long expiredMs) {
        Claims claims = Jwts.claims();
        claims.put("email", email);
        Header header = Jwts.header();
        header.put(Header.TYPE, Header.JWT_TYPE);

        return Jwts.builder()
                .setHeader((Map<String, Object>) header)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //JWT 만료 여부 체크 메서드
    public static boolean isExpired(String token, String secretKey) {
        Claims claims = parseClaims(token, secretKey);
        Long expiration = (Long) claims.get("exp");
        if (System.currentTimeMillis() <= expiration) {
            return false;
        }
        return true;
    }

    private static Claims parseClaims(String token, String secretKey) {
        Jwt parsedJwt = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parse(token);
        Claims claims = (Claims) parsedJwt.getBody();
        return claims;
    }
}
