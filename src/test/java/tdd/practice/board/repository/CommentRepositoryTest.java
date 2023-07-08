package tdd.practice.board.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tdd.practice.board.dto.Comment;
import tdd.practice.board.dto.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void saveComment() {
        Comment comment = new Comment();
        comment.setCommentContent("content");
        comment.setCommentWriter(1);
        comment.setCommentBoard(1);
        comment.setCommentOrder(1);
        comment.setCommentLevel(1);
        Comment savedComment = commentRepository.save(comment);
        log.info("commentNo={}", savedComment.getCommentNo());

        assertThat(savedComment.getCommentContent()).isEqualTo("content");
    }

    @Test
    void searchComment() {
        Member member = new Member();
        member.setMemberNickname("writer");
        Member savedMember = memberRepository.save(member);

        Comment comment = new Comment();
        comment.setCommentContent("content");
        comment.setCommentWriter(savedMember.getMemberNo());
        comment.setCommentBoard(1);
        comment.setCommentOrder(1);
        comment.setCommentLevel(1);
        Comment savedComment = commentRepository.save(comment);
        log.info("commentNo={}", savedComment.getCommentNo());

        List<Comment> savedCommentList = commentRepository.findByBoardNo(1);

        assertThat(savedCommentList.get(0).getMemberNickname()).isEqualTo("writer");
    }

    @Test
    void searchCommentList() {
        Member member = new Member();
        member.setMemberNickname("writer");
        Member savedMember = memberRepository.save(member);

        Comment comment = new Comment();
        comment.setCommentContent("content");
        comment.setCommentWriter(savedMember.getMemberNo());
        comment.setCommentBoard(1);
        comment.setCommentOrder(1);
        comment.setCommentLevel(1);
        Comment savedComment = commentRepository.save(comment);
        log.info("commentNo={}", savedComment.getCommentNo());

        Comment comment2 = new Comment();
        comment2.setCommentContent("content");
        comment2.setCommentWriter(savedMember.getMemberNo());
        comment2.setCommentBoard(1);
        comment2.setCommentOrder(1);
        comment2.setCommentLevel(1);
        Comment savedComment2 = commentRepository.save(comment2);
        log.info("commentNo={}", savedComment2.getCommentNo());

        List<Comment> savedCommentList = commentRepository.findByBoardNo(1);

        assertThat(savedCommentList.get(0).getMemberNickname()).isEqualTo("writer");
        assertThat(savedCommentList.get(1).getMemberNickname()).isEqualTo("writer");
    }
}
