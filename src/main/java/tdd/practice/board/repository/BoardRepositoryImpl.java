package tdd.practice.board.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import tdd.practice.board.dto.Board;
import tdd.practice.board.mapper.BoardMapper;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    @Override
    public Board save(Board board) {
        sqlSessionTemplate.getMapper(BoardMapper.class).save(board);
        return board;
    }

    @Override
    public Integer update(Integer boardNo) {
        return sqlSessionTemplate.getMapper(BoardMapper.class).update(boardNo);
    }

    @Override
    public Integer updateViews(Integer boardNo) {
        return sqlSessionTemplate.getMapper(BoardMapper.class).updateViews(boardNo);
    }

    @Override
    public Integer delete(Integer boardNo) {
        return sqlSessionTemplate.getMapper(BoardMapper.class).delete(boardNo);
    }

    @Override
    public Board findByBoardNo(Integer boardNo) {
        return sqlSessionTemplate.getMapper(BoardMapper.class).findByBoardNo(boardNo);
    }

    @Override
    public List<Board> findAll() {
        return sqlSessionTemplate.getMapper(BoardMapper.class).findAll();
    }
}