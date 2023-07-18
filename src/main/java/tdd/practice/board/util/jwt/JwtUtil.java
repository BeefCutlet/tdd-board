package tdd.practice.board.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    //JWT 생성 메서드
    public static String createJwt(String email, String secretKey, Long expiredMs) {
        Claims claims = Jwts.claims();
        claims.put("email", email);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //JWT 만료 여부 체크 메서드
    public static boolean isExpired(String token, String secretKey) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder().setSigningKey(key);
        return true;
    }
}
