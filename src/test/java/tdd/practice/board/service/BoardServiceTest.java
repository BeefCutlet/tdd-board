package tdd.practice.board.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tdd.practice.board.dto.Board;
import tdd.practice.board.dto.Member;
import tdd.practice.board.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void saveReply() {
        Member member = new Member();
        memberRepository.save(member);

        Board board1 = new Board();
        board1.setBoardWriter(member.getMemberNo());
        board1.setBoardTitle("title-1");
        board1.setBoardContent("content-1");
        board1.setBoardCategory("category-1");
        board1.setBoardSecret(1);
        log.info("boardTitle={}", board1.getBoardTitle());

        Board board2 = new Board();
        board2.setBoardWriter(member.getMemberNo());
        board2.setBoardTitle("title-1");
        board2.setBoardContent("content-1");
        board2.setBoardCategory("category-1");
        board2.setBoardSecret(1);
        log.info("boardTitle={}", board2.getBoardTitle());

        Board savedBoard1 = boardService.save(board1);
        Board savedBoard2 = boardService.save(board2);

        assertThat(savedBoard1.getBoardGroup()).isEqualTo(1);
        assertThat(savedBoard1.getBoardOrder()).isEqualTo(1);
        assertThat(savedBoard1.getBoardLevel()).isEqualTo(1);

        assertThat(savedBoard2.getBoardGroup()).isEqualTo(2);
        assertThat(savedBoard2.getBoardOrder()).isEqualTo(1);
        assertThat(savedBoard2.getBoardLevel()).isEqualTo(1);
    }

    @Test
    void searchBoardListTest() {
        Member member = new Member();
        memberRepository.save(member);

        for (int i = 0; i < 31; i++) {
            Board board = new Board();
            board.setBoardWriter(member.getMemberNo());
            board.setBoardTitle("title-" + (i+1));
            board.setBoardContent("content-" + (i+1));
            board.setBoardCategory("category-" + (i+1));
            board.setBoardSecret(1);
            boardService.save(board);
            log.info("boardTitle={}", board.getBoardTitle());
        }

        List<Board> boardList = boardService.searchList(1, "board_title", "tle-1");
        for (Board board : boardList) {
            log.info("boardTitle = {}", board.getBoardTitle());
        }
        assertThat(boardList.get(0).getBoardTitle()).isEqualTo("title-19");
    }

    @Test
    void searchBoardTest() {
        Member member = new Member();
        memberRepository.save(member);

        Board board = new Board();
        board.setBoardWriter(member.getMemberNo());
        board.setBoardTitle("title-1");
        board.setBoardContent("content-1");
        board.setBoardCategory("category-1");
        board.setBoardSecret(1);
        log.info("boardTitle={}", board.getBoardTitle());

        Board savedBoard = boardService.save(board);
        log.info("boardNo={}", savedBoard.getBoardNo());

        Board searchedBoard = boardService.search(savedBoard.getBoardNo());
        assertThat(searchedBoard).isNotNull();
        assertThat(searchedBoard.getBoardTitle()).isEqualTo("title-1");
        assertThat(searchedBoard.getBoardContent()).isEqualTo("content-1");
        assertThat(searchedBoard.getBoardCategory()).isEqualTo("category-1");
        assertThat(searchedBoard.getBoardGroup()).isEqualTo(1);
        assertThat(searchedBoard.getBoardOrder()).isEqualTo(1);
        assertThat(searchedBoard.getBoardLevel()).isEqualTo(1);
    }
}
