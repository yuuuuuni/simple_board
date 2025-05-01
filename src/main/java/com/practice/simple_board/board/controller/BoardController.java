package com.practice.simple_board.board.controller;

import com.practice.simple_board.board.service.BoardService;
import com.practice.simple_board.board.vo.BoardVO;
import com.practice.simple_board.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/create")
    public String boardCreateForm() {
        return "board/create";
    }

    @PostMapping("/create")
    public String boardCreate(@ModelAttribute BoardVO boardVO, HttpSession session) {
        MemberVO authorVO = (MemberVO) session.getAttribute("member");
        boardService.create(boardVO, authorVO);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String boardList(Model model) {
        List<BoardVO> boards = boardService.selectAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/detail/{id}")
    public String boardDetail(@PathVariable("id") Long id, Model model) {
        BoardVO boardVO = boardService.selectOneById(id);
        boardService.updateHit(boardVO);
        model.addAttribute("board", boardVO);
        return "board/detail";
    }

    @GetMapping("/detail/{id}/update")
    public String boardUpdateForm(@PathVariable("id") Long id, Model model) {
        BoardVO boardVO = boardService.selectOneById(id);
        model.addAttribute("board", boardVO);
        return "board/update";
    }

    @PostMapping("/detail/{id}/update")
    public String boardUpdate(@PathVariable("id") Long id, @ModelAttribute BoardVO boardVO) {
        BoardVO dbBoardVO = boardService.selectOneById(id);
        boardService.update(dbBoardVO, boardVO);
        return "redirect:/board/detail/{id}";
    }

    @PostMapping("/detail/{id}/delete")
    public String boardDelete(@PathVariable("id") Long id) {
        BoardVO boardVO = boardService.selectOneById(id);
        boardService.delete(boardVO);
        return "redirect:/board/list";
    }
}