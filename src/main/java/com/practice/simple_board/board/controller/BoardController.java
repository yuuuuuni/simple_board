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
    //count : 게시물이 총 몇개가 있는지의 변수(쿼리를 통해 구해야함)
    //totalPages : 총 페이지 갯수(Math.ceil() 함수를 통해 구해야함, count / size)
    /* required 뜻 : url '/board/list' 뒤에 '?page=1&size=5'와 같이 파라미터가 적혀 있어야만 페이지 접속이 되냐(true), 없어도 접속이 되냐(false)
    즉, url에 해당 파라미터가 필수로 들어와야지만 페이지가 뜨냐(true) or 들어오지 않아도 뜨냐(false)의 의미 */
    public String boardList(Model model,
                            @RequestParam(value = "kw", required = false, defaultValue = "") String kw,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        List<BoardVO> boards = boardService.search(kw, page, size); //검색 & 페이징이 적용된 현재 페이지의 게시글 리스트
        int count = boardService.countBoards(kw); //검색이 적용된 전체 게시글 수
        int totalPages = (int) Math.ceil((double) count / size); //검색이 적용된 전체 페이지 수
        model.addAttribute("boards", boards);
        model.addAttribute("kw", kw);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);
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