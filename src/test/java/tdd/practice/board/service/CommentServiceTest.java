package tdd.practice.board.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    //댓글 리스트 조회
    @Test
    void searchCommentList() {
        
    }
}
