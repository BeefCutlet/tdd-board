package tdd.practice.board.repository;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import tdd.practice.board.dto.Member;
import tdd.practice.board.mapper.MemberMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    @Override
    public Member save(Member member) {
        sqlSessionTemplate.getMapper(MemberMapper.class).save(member);
        return member;
    }

    @Override
    public Integer update(Member member) {
        return sqlSessionTemplate.getMapper(MemberMapper.class).update(member);
    }

    @Override
    public Integer delete(Integer memberNo) {
        return sqlSessionTemplate.getMapper(MemberMapper.class).delete(memberNo);
    }

    @Override
    public Member findByMemberNo(Integer memberNo) {
        return sqlSessionTemplate.getMapper(MemberMapper.class).findByMemberNo(memberNo);
    }

    @Override
    public List<Member> findAll() {
        return sqlSessionTemplate.getMapper(MemberMapper.class).findAll();
    }
}
