package com.practice.simple_board.controller;

import com.practice.simple_board.service.MemberService;
import com.practice.simple_board.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberVO memberVO) {
        memberService.join(memberVO);
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberVO memberVO, HttpSession session) {
        MemberVO loginVO = memberService.login(memberVO);

        if (loginVO == null) { //아이디와 패스워드가 틀린 경우
            return "redirect:/member/login";
        } else { //아이디와 패스워드가 맞는 경우
            session.setAttribute("member", loginVO);
            return "redirect:/";
        }
    }
}
