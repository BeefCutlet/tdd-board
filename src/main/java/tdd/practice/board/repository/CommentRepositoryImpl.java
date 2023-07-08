package tdd.practice.board.repository;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import tdd.practice.board.dto.Comment;
import tdd.practice.board.mapper.CommentMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    @Override
    public Comment save(Comment comment) {
        sqlSessionTemplate.getMapper(CommentMapper.class).save(comment);
        return comment;
    }

    @Override
    public Integer update(Comment comment) {
        return sqlSessionTemplate.getMapper(CommentMapper.class).update(comment);
    }

    @Override
    public Integer delete(int commentNo) {
        return sqlSessionTemplate.getMapper(CommentMapper.class).delete(commentNo);
    }

    @Override
    public List<Comment> findByBoardNo(int boardNo) {
        return sqlSessionTemplate.getMapper(CommentMapper.class).findByBoardNo(boardNo);
    }
}
