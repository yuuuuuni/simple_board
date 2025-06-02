package com.practice.simple_board.board.mapper;

import com.practice.simple_board.board.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    void create(BoardVO boardVO);

    List<BoardVO> selectAll();

    BoardVO selectOneById(Long id);

    void updateHit(BoardVO boardVO);

    void update(BoardVO dbBoardVO);

    void delete(BoardVO boardVO);

    List<BoardVO> search(@Param("kw") String kw, @Param("size") int size, @Param("offset") int offset);

    int countBoards(String kw);
}
