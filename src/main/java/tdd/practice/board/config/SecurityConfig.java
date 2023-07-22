package tdd.practice.board.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tdd.practice.board.security.filter.JwtLoginProcessingFilter;
import tdd.practice.board.security.handler.JwtLoginFailureHandler;
import tdd.practice.board.security.handler.JwtLoginSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/", "/login", "/login/**", "/logout", "/join").permitAll()
                .anyRequest().authenticated()
            .and()
                .addFilterBefore(jwtLoginProcessingFilter(http.getSharedObject(AuthenticationConfiguration.class)), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtLoginProcessingFilter jwtLoginProcessingFilter(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        JwtLoginProcessingFilter jwtLoginProcessingFilter = new JwtLoginProcessingFilter();
        jwtLoginProcessingFilter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        jwtLoginProcessingFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        jwtLoginProcessingFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return jwtLoginProcessingFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new JwtLoginSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new JwtLoginFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
