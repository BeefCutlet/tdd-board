package tdd.practice.board.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tdd.practice.board.util.jwt.JwtUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class JwtUtilTest {

    private String secretKey = "alsdfkjalsdfkjasdlfkjasdlfksdjflkldsjflsdsadasdfdsagerdkfjsadl";

    @Test
    void jwtParsing() {
        String jwt = JwtUtil.createJwt("TEST EMAIL", secretKey, 5 * 60 * 60 * 1000L);
        Jwt parsedJwt = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parse(jwt);
        Header header = parsedJwt.getHeader();
        Claims body = (Claims) parsedJwt.getBody();

        assertThat(header.get("typ")).isEqualTo("JWT");
        assertThat(header.get("alg")).isEqualTo("HS256");
        assertThat(body.get("email")).isEqualTo("TEST EMAIL");

        log.info("header.typ={}", header.get(Header.TYPE));
        log.info("header.alg={}", header.get("alg"));
        log.info("body.email={}", body.get("email"));
        log.info("body.exp={}", body.get("exp"));
    }

    @Test
    void parseJwtFailed() {
        String jwt = JwtUtil.createJwt("TEST EMAIL", secretKey, 5 * 60 * 60 * 1000L);
        assertThatThrownBy(() -> Jwts.parserBuilder().setSigningKey(secretKey + "aaa").build().parse(jwt))
                .isInstanceOf(SignatureException.class);
    }
}
