package tdd.practice.board.security.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tdd.practice.board.security.dto.Account;
import tdd.practice.board.security.token.JwtAuthenticationToken;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("JwtAuthenticationProvider 실행");
        //인증 요청 아이디/패스워드
        String loginId = authentication.getName();
        String password = (String) authentication.getCredentials();
        log.info("JwtProvider loginId={}", loginId);
        log.info("JwtProvider password={}", password);

        //DB에서 조회한 회원 정보
        Account account = (Account) userDetailsService.loadUserByUsername(loginId);

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new IllegalArgumentException("Invalid Password");
        }

        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(account, null, authentication.getAuthorities());

        return jwtAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
