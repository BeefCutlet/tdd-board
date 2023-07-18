package tdd.practice.board.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tdd.practice.board.dto.Member;
import tdd.practice.board.repository.MemberRepository;
import tdd.practice.board.security.dto.Account;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //회원 정보 검색
        Member member = memberRepository.findByMemberEmail(username);

        if (member == null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        //권한 리스트
        List<GrantedAuthority> roles = new ArrayList<>();
        //권한 리스트에 추가
        roles.add(new SimpleGrantedAuthority(member.getMemberAuth()));
        Account account = new Account(member, roles);

        return account;
    }
}
