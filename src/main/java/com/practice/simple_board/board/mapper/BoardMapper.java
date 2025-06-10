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

    //전달인자 값이 여러개인 경우 @Param을 통해 mapper.xml의 변수를 지정해줘야함
    List<BoardVO> search(@Param("kw") String kw, @Param("size") int size, @Param("offset") int offset);

    int countBoards(String kw);
}
