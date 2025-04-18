package com.practice.simple_board.board.service;

import com.practice.simple_board.board.mapper.BoardMapper;
import com.practice.simple_board.board.vo.BoardVO;
import com.practice.simple_board.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    public void create(BoardVO boardVO, MemberVO authorVO) {
        boardVO.setAuthor(authorVO.getMemberId());
        boardMapper.create(boardVO);
    }

    public List<BoardVO> selectAll() {
        return boardMapper.selectAll();
    }

    public BoardVO selectOneById(Long id) {
        return boardMapper.selectOneById(id);
    }

    public void updateHit(BoardVO boardVO) {
        int hit = boardVO.getHit();
        hit++;
        boardVO.setHit(hit);
        boardMapper.updateHit(boardVO);
    }
}
