package tdd.practice.board.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tdd.practice.board.dto.Member;
import tdd.practice.board.exception.MemberNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void saveAndFindMemberSuccess() {
        Member member = new Member();
        member.setMemberEmail("TEST EMAIL");
        member.setMemberNickname("TEST NICKNAME");
        member.setMemberPassword("TEST PASSWORD");

        Member savedMember = memberService.save(member);
        log.info("memberNo={}", savedMember.getMemberNo());
        Member findMember = memberService.findMember(savedMember.getMemberNo());

        assertThat(findMember.getMemberEmail()).isEqualTo("TEST EMAIL");
        assertThat(findMember.getMemberNickname()).isEqualTo("TEST NICKNAME");
        assertThat(findMember.getMemberPassword()).isEqualTo("TEST PASSWORD");
    }

    @Test
    void saveAndFindMemberFailed() {
        Member member = new Member();
        member.setMemberEmail("TEST EMAIL");
        member.setMemberNickname("TEST NICKNAME");
        member.setMemberPassword("TEST PASSWORD");

        Member savedMember = memberService.save(member);
        log.info("memberNo={}", savedMember.getMemberNo());

        assertThatThrownBy(() -> memberService.findMember(savedMember.getMemberNo()+1))
                .isInstanceOf(MemberNotFoundException.class);
    }

    @Test
    void updateMember() {
        Member member = new Member();
        member.setMemberEmail("TEST EMAIL");
        member.setMemberNickname("TEST NICKNAME");
        member.setMemberPassword("TEST PASSWORD");
        Member savedMember = memberService.save(member);
        log.info("memberNo={}", savedMember.getMemberNo());

        savedMember.setMemberNickname("TEST NICKNAME UPDATED");
        savedMember.setMemberPassword("TEST PASSWORD UPDATED");
        log.info("memberNickname={}", savedMember.getMemberNickname());
        log.info("memberPassword={}", savedMember.getMemberPassword());
        memberService.update(savedMember);

        Member findMember = memberService.findMember(savedMember.getMemberNo());
        assertThat(findMember.getMemberEmail()).isEqualTo("TEST EMAIL");
        assertThat(findMember.getMemberNickname()).isEqualTo("TEST NICKNAME UPDATED");
        assertThat(findMember.getMemberPassword()).isEqualTo("TEST PASSWORD UPDATED");
    }
}
