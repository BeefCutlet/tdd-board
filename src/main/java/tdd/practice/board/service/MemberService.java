package tdd.practice.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tdd.practice.board.dto.Member;
import tdd.practice.board.exception.MemberExistException;
import tdd.practice.board.exception.MemberNotFoundException;
import tdd.practice.board.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원 정보 저장
    public Member save(Member member) {
        log.info("save member email={}", member.getMemberEmail());
        log.info("save member pw={}", member.getMemberPassword());
        //회원이 존재하면 MemberExistException 발생
        if (memberRepository.findByMemberEmail(member.getMemberEmail()) != null) {
            throw new MemberExistException("member is already exist");
        }
        
        //비밀번호 암호화
        member.setMemberPassword(passwordEncoder.encode(member.getMemberPassword()));
        //회원 정보를 DB에 저장
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    //회원 정보 수정
    public void update(Member member) {
        log.info("update member email={}", member.getMemberEmail());
        log.info("update member pw={}", member.getMemberPassword());
        memberRepository.update(member);
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
