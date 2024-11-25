package spring_study.springmvc.boards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_study.springmvc.boards.dto.CreateBoardRequestDto;
import spring_study.springmvc.boards.dto.CreateBoardResponseDto;
import spring_study.springmvc.domain.Board;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository {

    Board save( Board board ); // 게시글 생성

    List< Board > findAll(); // 게시글 목록조회

    Optional<Board> findById( double id); // 게시글 조회

    // 게시글 수정
    // 게시글 삭제

}
