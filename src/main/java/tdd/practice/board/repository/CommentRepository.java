package tdd.practice.board.repository;

import tdd.practice.board.dto.Comment;

import java.util.List;

public interface CommentRepository {

    Comment save(Comment comment);
    Integer update(Comment comment);
    Integer delete(int commentNo);
    List<Comment> findByBoardNo(int boardNo);
}
