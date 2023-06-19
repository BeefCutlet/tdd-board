package tdd.practice.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import tdd.practice.board.dto.Member;

import java.util.List;

@Mapper
public interface MemberMapper {

    Integer save(Member member);
    Integer update(Member member);
    Integer delete(Integer memberNo);
    Member findByMemberNo(Integer memberNo);
    List<Member> findAll();
}
