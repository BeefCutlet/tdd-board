package tdd.practice.board.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import tdd.practice.board.dto.Member;

import java.util.Collection;


public class Account extends User {

    private final Member member;

    public Account(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getMemberEmail(), member.getMemberPassword(), authorities);
        this.member = member;
    }
}
