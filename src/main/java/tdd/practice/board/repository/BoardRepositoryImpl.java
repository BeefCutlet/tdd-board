package tdd.practice.board.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import tdd.practice.board.dto.Board;
import tdd.practice.board.mapper.BoardMapper;
import tdd.practice.board.util.pager.PageInfo;

import java.util.List;
import java.util.Map;

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
    public Integer update(Board board) {
        return sqlSessionTemplate.getMapper(BoardMapper.class).update(board);
    }

    @Override
    public Integer updateViews(Integer boardNo) {
        return sqlSessionTemplate.getMapper(BoardMapper.class).updateViews(boardNo);
    }

    @Override
    public Integer updateBoardOrder(Board board) {
        return sqlSessionTemplate.getMapper(BoardMapper.class).updateBoardOrder(board);
    }

    @Override
    public Integer delete(Integer boardNo) {
        return sqlSessionTemplate.getMapper(BoardMapper.class).delete(boardNo);
    }

    @Override
    public Integer findCount(Map<String, String> searchCondition) {
        return sqlSessionTemplate.getMapper(BoardMapper.class).findCount(searchCondition);
    }

    @Override
    public Integer findOrderCount(int boardGroup) {
        return sqlSessionTemplate.getMapper(BoardMapper.class).findOrderCount(boardGroup);
    }

    @Override
    public Integer findLastGroup() {
        return sqlSessionTemplate.getMapper(BoardMapper.class).findLastGroup();
    }

    @Override
    public Board findByBoardNo(Integer boardNo) {
        return sqlSessionTemplate.getMapper(BoardMapper.class).findByBoardNo(boardNo);
    }

    @Override
    public List<Board> findAll() {
        return sqlSessionTemplate.getMapper(BoardMapper.class).findAll();
    }

    @Override
    public List<Board> findAllBySearchCondition(PageInfo pageInfo) {
        return sqlSessionTemplate.getMapper(BoardMapper.class).findAllBySearchCondition(pageInfo);
    }
}
