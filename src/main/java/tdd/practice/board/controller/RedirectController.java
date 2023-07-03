package tdd.practice.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tdd.practice.board.dto.Board;
import tdd.practice.board.service.BoardService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RedirectController {

    private final BoardService boardService;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login-form";
    }

    @GetMapping("/board/main")
    public String boardMain(Model model) {
        List<Board> boardList = boardService.searchList(1, null, null);
        model.addAttribute("boardList", boardList);
        return "board/main";
    }

    @GetMapping("/board/write")
    public String boardWrite(@PathVariable int boardNo, Model model) {
        Board board = boardService.search(boardNo);
        model.addAttribute("board", board);
        return "board/write";
    }

    @GetMapping("/board/edit")
    public String boardEdit(@PathVariable int boardNo, Model model) {
        Board board = boardService.search(boardNo);
        model.addAttribute("board", board);
        return "board/edit";
    }

}
