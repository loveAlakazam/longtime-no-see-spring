package spring_study.springmvc.boards;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_study.springmvc.boards.dto.CreateBoardRequestDto;
import spring_study.springmvc.boards.dto.CreateBoardResponseDto;
import spring_study.springmvc.domain.Board;

public interface BoardRepository {

    Board save( Board board ); // 게시글 생성

}
