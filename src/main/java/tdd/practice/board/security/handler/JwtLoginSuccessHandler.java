package tdd.practice.board.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import tdd.practice.board.security.dto.Account;
import tdd.practice.board.util.jwt.JwtUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${jwt.secret}")
    private String secretKey;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("JwtLoginSuccessHandler.onAuthenticationSuccess 실행");
        //인증된 정보
        Account account = (Account) authentication.getPrincipal();
        log.info("Account Username={}", account.getUsername());
        //JWT 생성
        String jwt = JwtUtil.createJwt(account.getUsername(), secretKey, 1 * 60 * 1000L);
        response.setStatus(HttpStatus.OK.value());
        response.setHeader(HttpHeaders.AUTHORIZATION, jwt);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        //JWT 생성 확인용
        objectMapper.writeValue(response.getWriter(), jwt);
    }
}
