package tdd.practice.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tdd.practice.board.dto.Comment;
import tdd.practice.board.exception.CommentNotFoundException;
import tdd.practice.board.exception.SaveFailedException;
import tdd.practice.board.repository.CommentRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    //댓글 저장
    @Transactional
    public Comment save(Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        if (savedComment.getCommentNo() == null) {
            throw new SaveFailedException("댓글 저장 실패");
        }
        return savedComment;
    }

    //댓글 수정
    @Transactional
    public void update(Comment comment) {
        commentRepository.update(comment);
    }

    //댓글 리스트 조회
    @Transactional
    public List<Comment> searchList(int boardNo) {
        List<Comment> commentList = commentRepository.findByBoardNo(boardNo);
        return commentList;
    }
}
