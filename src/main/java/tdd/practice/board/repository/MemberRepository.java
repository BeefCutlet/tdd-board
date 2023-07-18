package tdd.practice.board.repository;

import tdd.practice.board.dto.Member;

import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    Integer update(Member member);
    Integer delete(Integer memberNo);
    Member findByMemberNo(Integer memberNo);
    Member findByMemberEmail(String memberEmail);
    List<Member> findAll();
}
