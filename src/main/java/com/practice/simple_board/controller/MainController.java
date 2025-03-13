package com.practice.simple_board.controller;

import com.practice.simple_board.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        model.addAttribute("member", memberVO);
        return "index";
    }
}
