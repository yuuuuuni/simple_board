package com.practice.simple_board.service;

import com.practice.simple_board.mapper.MemberMapper;
import com.practice.simple_board.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public void join(MemberVO memberVO) {
        memberMapper.join(memberVO);
    }
}
