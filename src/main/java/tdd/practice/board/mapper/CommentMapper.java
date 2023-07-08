package tdd.practice.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import tdd.practice.board.dto.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {

    Integer save(Comment comment);
    Integer update(Comment comment);
    Integer delete(int commentNo);
    List<Comment> findByBoardNo(int boardNo);
}
