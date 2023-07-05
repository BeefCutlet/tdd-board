package tdd.practice.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public Board save(Board board) {
        Board savedBoard = boardRepository.save(board);
        if (savedBoard.getBoardNo() == null) {
            throw new SaveFailedException("Save Failed");
        }
        return savedBoard;
    }

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
