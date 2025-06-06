package com.practice.simple_board.member.controller;

import com.practice.simple_board.member.service.MemberService;
import com.practice.simple_board.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        if (loginVO == null) { //아이디와 패스워드가 틀린 경우, 탈퇴여부가 N이 아닌 경우
            return "redirect:/member/login";
        } else { //아이디와 패스워드가 맞는 경우, 탈퇴여부가 N인 경우
            session.setAttribute("member", loginVO);
            return "redirect:/";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("member");
        session.invalidate(); //세션 무효화
        return "redirect:/member/login";
    }

    @GetMapping("/mypage/{memberId}")
    public String mypage(@PathVariable("memberId") String memberId, Model model) {
        MemberVO memberVO = memberService.selectOneByMemberId(memberId);
        model.addAttribute("member", memberVO);
        return "member/mypage";
    }

    @GetMapping("/mypage/{memberId}/update")
    public String memberUpdateForm(@PathVariable("memberId") String memberId, Model model) {
        MemberVO memberVO = memberService.selectOneByMemberId(memberId);
        model.addAttribute("member", memberVO);
        return "member/update";
    }

    @PostMapping("/mypage/{memberId}/update")
    public String memberUpdate(@PathVariable("memberId") String memberId, @ModelAttribute MemberVO memberVO) {
        MemberVO dbMemberVO = memberService.selectOneByMemberId(memberId);
        memberService.update(dbMemberVO, memberVO);
        return "redirect:/member/mypage/{memberId}";
    }

    @PostMapping("/mypage/{memberId}/delete")
    public String memberDelete(@PathVariable("memberId") String memberId) {
        MemberVO memberVO = memberService.selectOneByMemberId(memberId);
        memberService.delete(memberVO);
        return "redirect:/member/login";
    }
}
