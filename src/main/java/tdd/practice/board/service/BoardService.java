package tdd.practice.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tdd.practice.board.dto.Board;
import tdd.practice.board.exception.BoardNotFoundException;
import tdd.practice.board.exception.SaveFailedException;
import tdd.practice.board.repository.BoardRepository;
import tdd.practice.board.util.pager.Pager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {

    private static final Integer PAGE_SIZE = 10;
    private static final Integer BLOCK_SIZE = 5;
    private final BoardRepository boardRepository;

    @Transactional
    public Board save(Board board) {
        if (board.getBoardGroup() == null) {
            //최종 그룹 번호 조회
            Integer boardGroupCount = boardRepository.findLastGroup();
            //새 글 등록
            if (boardGroupCount == null) {
                board.setBoardGroup(1);
            } else {
                board.setBoardGroup(boardGroupCount + 1);
            }
            board.setBoardOrder(1);
            board.setBoardLevel(1);
        } else {
            //답글 등록
            Integer boardOrderCount = boardRepository.findOrderCount(board.getBoardGroup());
            if (board.getBoardLevel() == 1) {
                //원글에 답글 달기
                board.setBoardOrder(boardOrderCount + 1);
            } else {
                //답글에 답글 달기
                boardRepository.updateBoardOrder(board); //답글 순서 뒤로 미루기
                board.setBoardOrder(board.getBoardOrder() + 1);
            }
            board.setBoardGroup(board.getBoardGroup());
            board.setBoardLevel(board.getBoardLevel() + 1);
        }

        Board savedBoard = boardRepository.save(board);
        if (savedBoard.getBoardNo() == null) {
            throw new SaveFailedException("Save Failed");
        }

        return savedBoard;
    }

    @Transactional
    public void update(Board board) {
        boardRepository.update(board);
    }

    public Board search(int boardNo) {
        Board board = boardRepository.findByBoardNo(boardNo);
        if (board == null) {
            throw new BoardNotFoundException("Board Not Found, boardNo=" + boardNo);
        }
        return board;
    }

    public List<Board> searchList(int currentPage, String condition, String keyword) {
        Map<String, String> searchCondition = new HashMap<>();
        searchCondition.put("keyword", keyword);
        searchCondition.put("condition", condition);
        Integer totalBoard = boardRepository.findCount(searchCondition);
        Pager pager = Pager.builder()
                .pageSize(PAGE_SIZE)
                .blockSize(BLOCK_SIZE)
                .currentPage(currentPage)
                .totalBoard(totalBoard)
                .keyword(searchCondition.get("keyword"))
                .condition(searchCondition.get("condition"))
                .build();
        List<Board> boardList = boardRepository.findAllBySearchCondition(pager.getPageInfo());
        return boardList;
    }
}
