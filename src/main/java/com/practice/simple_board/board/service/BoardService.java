package com.practice.simple_board.board.service;

import com.practice.simple_board.board.mapper.BoardMapper;
import com.practice.simple_board.board.vo.BoardVO;
import com.practice.simple_board.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        boardVO.setHit(boardVO.getHit()+1);
        boardMapper.updateHit(boardVO);
    }

    public void update(BoardVO dbBoardVO, BoardVO boardVO) {
        dbBoardVO.setTitle(boardVO.getTitle());
        dbBoardVO.setContent(boardVO.getContent());
        boardMapper.update(dbBoardVO);
    }

    public void delete(BoardVO boardVO) {
        boardMapper.delete(boardVO);
    }
}
