package tdd.practice.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tdd.practice.board.dto.Member;
import tdd.practice.board.exception.MemberNotFoundException;
import tdd.practice.board.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 정보 저장
    public Member saveMember(Member member) {
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    //단일 회원 정보 가져오기
    public Member findMember(Integer memberNo) {
        Member member = memberRepository.findByMemberNo(memberNo);
        if (member == null) {
            throw new MemberNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
        return member;
    }
}
