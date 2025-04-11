package com.practice.simple_board.member.service;

import com.practice.simple_board.member.mapper.MemberMapper;
import com.practice.simple_board.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public void join(MemberVO memberVO) {
        memberMapper.join(memberVO);
    }

    public MemberVO login(MemberVO memberVO) {
        return memberMapper.login(memberVO);
    }

    public MemberVO selectOneByMemberId(String memberId) {
        return memberMapper.selectOneByMemberId(memberId);
    }

    public void memberUpdate(MemberVO dbMemberVO, MemberVO memberVO) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!! memberVO.getPassword: " + memberVO.getPassword());
        if (!dbMemberVO.getPassword().equals(memberVO.getPassword()) && !memberVO.getPassword().isEmpty()) {
            dbMemberVO.setPassword(memberVO.getPassword());
        }
            dbMemberVO.setPhone(memberVO.getPhone());
            dbMemberVO.setEmail(memberVO.getEmail());
            memberMapper.memberUpdate(dbMemberVO);
    }

    public void memberDelete(MemberVO memberVO) {
        memberMapper.memberDelete(memberVO);
    }
}
