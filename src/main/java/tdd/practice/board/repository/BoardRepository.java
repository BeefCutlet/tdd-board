package tdd.practice.board.repository;

import tdd.practice.board.dto.Board;
import tdd.practice.board.util.pager.PageInfo;

import java.util.List;
import java.util.Map;

public interface BoardRepository {
    Board save(Board board);
    Integer update(Board board);
    Integer updateViews(Integer boardNo);
    Integer updateBoardOrder(Board board);
    Integer delete(Integer boardNo);
    Integer findCount(Map<String, String> searchCondition);
    Integer findOrderCount(int boardGroup);
    Integer findLastGroup();
    Board findByBoardNo(Integer boardNo);
    List<Board> findAll();
    List<Board> findAllBySearchCondition(PageInfo pageInfo);
}
