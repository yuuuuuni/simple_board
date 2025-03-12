package com.practice.simple_board.mapper;

import com.practice.simple_board.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void join(MemberVO memberVO);

    MemberVO login(MemberVO memberVO);
}
