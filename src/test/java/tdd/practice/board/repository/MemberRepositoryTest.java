package tdd.practice.board.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tdd.practice.board.dto.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void saveAndFindMember() {
        Member member = new Member();
        member.setMemberEmail("TEST EMAIL");
        member.setMemberNickname("TEST NICKNAME");
        member.setMemberPassword("TEST PASSWORD");

        Member savedMember = memberRepository.save(member);
        log.info("memberNo={}", savedMember.getMemberNo());
        Member findMember = memberRepository.findByMemberNo(savedMember.getMemberNo());

        assertThat(findMember.getMemberEmail()).isEqualTo("TEST EMAIL");
        assertThat(findMember.getMemberNickname()).isEqualTo("TEST NICKNAME");
        assertThat(findMember.getMemberPassword()).isEqualTo("TEST PASSWORD");
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setMemberEmail("TEST EMAIL-1");
        member1.setMemberNickname("TEST NICKNAME-1");
        member1.setMemberPassword("TEST PASSWORD-1");

        Member member2 = new Member();
        member2.setMemberEmail("TEST EMAIL-2");
        member2.setMemberNickname("TEST NICKNAME-2");
        member2.setMemberPassword("TEST PASSWORD-2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> memberList = memberRepository.findAll();
        for (int i = 0; i < memberList.size(); i++) {
            assertThat(memberList.get(i).getMemberEmail()).isEqualTo("TEST EMAIL-" + (i+1));
            assertThat(memberList.get(i).getMemberNickname()).isEqualTo("TEST NICKNAME-" + (i+1));
            assertThat(memberList.get(i).getMemberPassword()).isEqualTo("TEST PASSWORD-" + (i+1));
        }
    }

    @Test
    void delete() {
        Member member = new Member();
        member.setMemberEmail("TEST EMAIL");
        member.setMemberNickname("TEST NICKNAME");
        member.setMemberPassword("TEST PASSWORD");

        Member savedMember = memberRepository.save(member);
        log.info("savedMember.memberNo={}", savedMember.getMemberNo());

        Member findMember = memberRepository.findByMemberNo(savedMember.getMemberNo());
        log.info("findMember.memberNo={}", findMember.getMemberNo());
        assertThat(findMember.getMemberNo()).isEqualTo(savedMember.getMemberNo());

        memberRepository.delete(savedMember.getMemberNo());
        Member deletedMember = memberRepository.findByMemberNo(savedMember.getMemberNo());
        assertThatThrownBy(() -> deletedMember.getMemberNo()).isInstanceOf(NullPointerException.class);
    }
}