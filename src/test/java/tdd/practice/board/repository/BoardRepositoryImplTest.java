package tdd.practice.board.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tdd.practice.board.dto.Board;
import tdd.practice.board.dto.Member;
import tdd.practice.board.util.pager.PageInfo;
import tdd.practice.board.util.pager.Pager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    void searchWithNoKeyword() {
        Member member = new Member();
        memberRepository.save(member);

        for (int i = 0; i < 31; i++) {
            Board board = new Board();
            board.setBoardWriter(member.getMemberNo());
            board.setBoardTitle("title-" + (i+1));
            board.setBoardContent("content-" + (i+1));
            board.setBoardCategory("category-" + (i+1));
            board.setBoardSecret(1);
            board.setBoardGroup(i+1);
            board.setBoardOrder(i+1);
            board.setBoardLevel(i+1);
            boardRepository.save(board);
        }

        Map<String, String> map = new HashMap<>();
        Integer totalBoard = boardRepository.findCount(map);
        assertThat(totalBoard).isEqualTo(31);

        Pager pager = Pager.builder()
                .currentPage(2)
                .pageSize(10)
                .blockSize(5)
                .totalBoard(totalBoard)
                .build();
        PageInfo pageInfo = pager.getPageInfo();

        assertThat(pageInfo.getStartPage()).isEqualTo(1);
        assertThat(pageInfo.getEndPage()).isEqualTo(4);
        assertThat(pageInfo.getStartRow()).isEqualTo(11);
        assertThat(pageInfo.getEndRow()).isEqualTo(20);
        assertThat(pageInfo.getPageSize()).isEqualTo(10);

        List<Board> boardList = boardRepository.findAllBySearchCondition(pageInfo);
        for (Board board : boardList) {
            log.info("boardTitle = {}", board.getBoardTitle());
        }
        assertThat(boardList.get(0).getBoardTitle()).isEqualTo("title-21");
    }

    @Test
    void searchWithKeyword() {
        Member member = new Member();
        memberRepository.save(member);

        //임시 게시글 31개 입력
        for (int i = 0; i < 31; i++) {
            Board board = new Board();
            board.setBoardWriter(member.getMemberNo());
            board.setBoardTitle("title-" + (i+1));
            board.setBoardContent("content-" + (i+1));
            board.setBoardCategory("category-" + (i+1));
            board.setBoardSecret(1);
            board.setBoardGroup(i+1);
            board.setBoardOrder(i+1);
            board.setBoardLevel(i+1);
            boardRepository.save(board);
            log.info("boardTitle={}", board.getBoardTitle());
        }

        //검색조건 : 제목
        //검색내용 : tle-1
        Map<String, String> searchCondition = new HashMap<>();
        searchCondition.put("condition", "board_title");
        searchCondition.put("keyword", "tle-1");
        //검색 조건에 맞는 게시글 개수
        Integer totalBoard = boardRepository.findCount(searchCondition);
        assertThat(totalBoard).isEqualTo(11);

        //검색
        //현재 페이지 : 1
        //페이지 당 게시글 수 : 10
        //페이지 블럭 당 페이지 수 : 5
        //검색조건 : 제목
        //검색내용 : tle-1
        Pager pager = Pager.builder()
                .currentPage(1)
                .pageSize(10)
                .blockSize(5)
                .totalBoard(totalBoard)
                .condition(searchCondition.get("condition"))
                .keyword(searchCondition.get("keyword"))
                .build();
        PageInfo pageInfo = pager.getPageInfo();
        log.info("condition={}", pageInfo.getCondition());
        log.info("keyword={}", pageInfo.getKeyword());

        assertThat(pageInfo.getStartPage()).isEqualTo(1);
        assertThat(pageInfo.getEndPage()).isEqualTo(2);
        assertThat(pageInfo.getStartRow()).isEqualTo(1);
        assertThat(pageInfo.getEndRow()).isEqualTo(10);
        assertThat(pageInfo.getPageSize()).isEqualTo(10);

        List<Board> boardList = boardRepository.findAllBySearchCondition(pageInfo);
        for (Board board : boardList) {
            log.info("boardTitle = {}", board.getBoardTitle());
        }
        assertThat(boardList.get(0).getBoardTitle()).isEqualTo("title-19");
    }
}