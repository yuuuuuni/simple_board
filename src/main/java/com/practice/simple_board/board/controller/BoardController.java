package com.practice.simple_board.board.controller;

import com.practice.simple_board.board.service.BoardService;
import com.practice.simple_board.board.vo.BoardVO;
import com.practice.simple_board.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
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
    //page : 현재 몇페이지에 있는지의 변수(초기값은 1페이지에 있어야 하므로 defaultValue = "1" 설정)
    //size : 한 페이지에 몇개씩 보여줄지의 변수(한 페이지당 5개씩 보여줄 것이므로 defaultValue = "5" 설정)
    //count : 리스트 전체에 게시물이 몇개가 있는지의 변수(구해야함)
    //totalPages : 총 페이지 갯수(구해야함, count / size)
    public String boardList(Model model,
                            @RequestParam(value = "kw", required = false, defaultValue = "") String kw,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        List<BoardVO> boards = boardService.search(kw, page, size);
        int count = boardService.countBoards(kw);
        int totalPages = (int) Math.ceil((double) count / size);
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