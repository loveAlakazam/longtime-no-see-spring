package spring_study.springmvc.boards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_study.springmvc.boards.dto.CreateBoardRequestDto;
import spring_study.springmvc.boards.dto.CreateBoardResponseDto;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시판 생성
    public CreateBoardResponseDto create( CreateBoardRequestDto dto ) {
        return new CreateBoardResponseDto();
    }
}
