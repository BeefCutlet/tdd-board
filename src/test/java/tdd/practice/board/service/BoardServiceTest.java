package tdd.practice.board.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tdd.practice.board.dto.Board;
import tdd.practice.board.dto.Member;
import tdd.practice.board.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private MemberRepository memberRepository;

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
            board.setBoardGroup(0);
            board.setBoardOrder(0);
            board.setBoardLevel(0);
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
        board.setBoardGroup(0);
        board.setBoardOrder(0);
        board.setBoardLevel(0);
        boardService.save(board);
        log.info("boardTitle={}", board.getBoardTitle());

        Board savedBoard = boardService.save(board);
        log.info("boardNo={}", savedBoard.getBoardNo());

        Board searchedBoard = boardService.search(savedBoard.getBoardNo());
        assertThat(searchedBoard).isNotNull();
        assertThat(searchedBoard.getBoardTitle()).isEqualTo("title-1");
        assertThat(searchedBoard.getBoardContent()).isEqualTo("content-1");
        assertThat(searchedBoard.getBoardCategory()).isEqualTo("category-1");
    }
}
