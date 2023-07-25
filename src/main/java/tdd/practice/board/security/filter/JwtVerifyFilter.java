package tdd.practice.board.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.web.servlet.OAuth2ClientDsl;
import org.springframework.web.filter.OncePerRequestFilter;
import tdd.practice.board.util.jwt.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtVerifyFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtVerifyFilter 시작");
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization={}", authorization);

        //유효성 검사
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("Authorization가 유효하지 않습니다.");
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorization.split(" ")[1];

        //만료기간 검사 + SecretKey 유효성 검사(실패 시 SignatureException 발생)
        boolean isExpired = JwtUtil.isExpired(token, secretKey);
        if (isExpired) {
            log.error("Token is Expired");
            filterChain.doFilter(request, response);
        }
    }
}
