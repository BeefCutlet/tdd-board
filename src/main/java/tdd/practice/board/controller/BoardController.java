package tdd.practice.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tdd.practice.board.dto.Board;
import tdd.practice.board.service.BoardService;

import java.util.List;

@RestController("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
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


}
