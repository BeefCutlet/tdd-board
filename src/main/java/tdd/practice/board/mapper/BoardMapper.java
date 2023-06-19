package tdd.practice.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import tdd.practice.board.dto.Board;

import java.util.List;

@Mapper
public interface BoardMapper {

    Integer save(Board board);
    Integer update(Integer boardNo);
    Integer updateViews(Integer boardNo);
    Integer delete(Integer boardNo);
    Board findByBoardNo(Integer boardNo);
    List<Board> findAll();
}
