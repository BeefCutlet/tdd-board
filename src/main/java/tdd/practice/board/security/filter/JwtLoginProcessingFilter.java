package tdd.practice.board.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import tdd.practice.board.dto.Member;
import tdd.practice.board.security.token.JwtAuthenticationToken;
import tdd.practice.board.util.jwt.JwtUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    ObjectMapper objectMapper = new ObjectMapper();

    public JwtLoginProcessingFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //요청 JSON을 Member 객체에 담기
        Member member = objectMapper.readValue(request.getReader(), Member.class);
        log.info("memberEmail={}", member.getMemberEmail());
        log.info("memberPassword={}", member.getMemberPassword());
        if (!StringUtils.hasText(member.getMemberEmail()) || !StringUtils.hasText(member.getMemberPassword())) {
            throw new IllegalArgumentException("Email or Password is Empty");
        }

        //JWT 인증을 위한 Authentication 객체
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(member.getMemberEmail(), member.getMemberPassword());
        log.info("jwt Email={}", jwtAuthenticationToken.getPrincipal());
        log.info("jwt Password={}", jwtAuthenticationToken.getCredentials());

        //AuthenticationManager 객체로 인증 진행
        Authentication authenticatedToken = getAuthenticationManager().authenticate(jwtAuthenticationToken);
        log.info("authenticatedTokenPrincipal={}", authenticatedToken.getPrincipal().getClass());
        return authenticatedToken;
    }
}
