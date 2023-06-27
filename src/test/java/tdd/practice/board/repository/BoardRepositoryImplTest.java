package tdd.practice.board.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tdd.practice.board.dto.Board;
import tdd.practice.board.dto.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class BoardRepositoryImplTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void saveAndFind() {
        Member member = new Member();
        memberRepository.save(member);

        Board board = new Board();
        board.setBoardWriter(member.getMemberNo());
        board.setBoardTitle("title-1");
        board.setBoardContent("content-1");
        board.setBoardCategory("category-1");
        board.setBoardSecret(1);
        board.setBoardGroup(0);
        board.setBoardOrder(0);
        board.setBoardLevel(0);

        boardRepository.save(board);
        boardRepository.findByBoardNo(board.getBoardNo());
        assertThat(board.getBoardTitle()).isEqualTo("title-1");
        assertThat(board.getBoardContent()).isEqualTo("content-1");
        assertThat(board.getBoardCategory()).isEqualTo("category-1");
    }

    @Test
    void findList() {
        Member member = new Member();
        memberRepository.save(member);

        Board board1 = new Board();
        board1.setBoardWriter(member.getMemberNo());
        board1.setBoardTitle("title-1");
        board1.setBoardContent("content-1");
        board1.setBoardCategory("category-1");
        board1.setBoardSecret(1);
        board1.setBoardGroup(0);
        board1.setBoardOrder(0);
        board1.setBoardLevel(0);

        Board board2 = new Board();
        board2.setBoardWriter(member.getMemberNo());
        board2.setBoardTitle("title-2");
        board2.setBoardContent("content-2");
        board2.setBoardCategory("category-2");
        board2.setBoardSecret(1);
        board2.setBoardGroup(0);
        board2.setBoardOrder(0);
        board2.setBoardLevel(0);

        boardRepository.save(board1);
        boardRepository.save(board2);

        List<Board> boardList = boardRepository.findAll();
        for (int i = 0; i < boardList.size(); i++) {
            log.info("boardNo = {}", boardList.get(i).getBoardNo());
            assertThat(boardList.get(i).getBoardTitle()).isEqualTo("title-" + (i+1));
            assertThat(boardList.get(i).getBoardContent()).isEqualTo("content-" + (i+1));
            assertThat(boardList.get(i).getBoardCategory()).isEqualTo("category-" + (i+1));
        }
    }
}