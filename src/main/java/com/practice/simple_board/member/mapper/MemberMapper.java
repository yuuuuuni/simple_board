package com.practice.simple_board.member.mapper;

import com.practice.simple_board.member.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void join(MemberVO memberVO);

    MemberVO login(MemberVO memberVO);

    MemberVO selectOneByMemberId(String memberId);

    void update(MemberVO dbMemberVO);

    void delete(MemberVO memberVO);
}
