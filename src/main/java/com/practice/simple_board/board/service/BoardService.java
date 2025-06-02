package com.practice.simple_board.board.service;

import com.practice.simple_board.board.mapper.BoardMapper;
import com.practice.simple_board.board.vo.BoardVO;
import com.practice.simple_board.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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
        boardVO.setHit(boardVO.getHit() + 1);
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

    public List<BoardVO> search(String kw, int page, int size) {
        int offset = (page - 1) * size; //이전 페이지들의 게시글 갯수
        return boardMapper.search(kw, size, offset);
    }

    public int countBoards(String kw) {
        return boardMapper.countBoards(kw);
    }
}
