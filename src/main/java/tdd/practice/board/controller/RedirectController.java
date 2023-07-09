package tdd.practice.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tdd.practice.board.dto.Board;
import tdd.practice.board.dto.Comment;
import tdd.practice.board.service.BoardService;
import tdd.practice.board.service.CommentService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RedirectController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login-form";
    }

    @GetMapping("/board/main")
    public String boardMain() {
        return "board/main";
    }

    @GetMapping("/board/detail/{boardNo}")
    public String boardDetail(@PathVariable int boardNo, Model model) {
        Board searchedBoard = boardService.search(boardNo);
        List<Comment> commentList = commentService.searchList(boardNo);
        model.addAttribute("board", searchedBoard);
        model.addAttribute("commentList", commentList);
        return "board/detail";
    }

    @GetMapping("/board/write/{boardNo}")
    public String boardWrite(@PathVariable(required = false) Integer boardNo, Model model) {
        Board board = null;
        if (boardNo != null) {
            board = boardService.search(boardNo);
        }
        model.addAttribute("board", board);

        return "board/write";
    }

    @GetMapping("/board/edit/{boardNo}")
    public String boardEdit(@PathVariable int boardNo, Model model) {
        Board board = boardService.search(boardNo);
        model.addAttribute("board", board);
        return "board/edit";
    }

}
