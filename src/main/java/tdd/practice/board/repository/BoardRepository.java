package tdd.practice.board.repository;

import tdd.practice.board.dto.Board;

import java.util.List;

public interface BoardRepository {
    Board save(Board board);
    Integer update(Integer boardNo);
    Integer updateViews(Integer boardNo);
    Integer delete(Integer boardNo);
    Board findByBoardNo(Integer boardNo);
    List<Board> findAll();
}
