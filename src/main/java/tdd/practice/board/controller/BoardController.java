package tdd.practice.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tdd.practice.board.dto.Board;
import tdd.practice.board.service.BoardService;

import java.util.List;

@RestController("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/search")
    public List<Board> boardList(
            @RequestParam(required = false) Integer currentPage,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String condition) {
        if (currentPage == null) {
            currentPage = 1;
        }
        List<Board> boardList = boardService.searchList(currentPage, keyword, condition);
        return boardList;
    }

    @PutMapping("/write")
    public String writeBoard(@RequestBody Board board) {
        boardService.save(board);
        return "/board/main";
    }

    @PutMapping("/edit/{boardNo}")
    public String editBoard(@RequestBody Board board) {
        boardService.update(board);
        return "/board/main";
    }
}
