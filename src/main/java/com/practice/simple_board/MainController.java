package com.practice.simple_board;

import com.practice.simple_board.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        //session에서 로그인 정보를 가져올 때 Object 타입으로 가져오므로 사용할 타입에 맞게 형변환 필요
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        model.addAttribute("member", memberVO);
        return "index";
    }
}
