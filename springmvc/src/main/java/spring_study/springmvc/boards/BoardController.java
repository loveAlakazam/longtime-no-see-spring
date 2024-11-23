package spring_study.springmvc.boards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import spring_study.springmvc.boards.dto.CreateBoardRequestDto;
import spring_study.springmvc.boards.dto.CreateBoardResponseDto;
import spring_study.springmvc.domain.Board;
import spring_study.springmvc.service.MemberService;

@Controller
public class BoardController {

    private final BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("board")
    @ResponseBody
    public CreateBoardResponseDto create ( @RequestBody()CreateBoardRequestDto request ) {
        return new CreateBoardResponseDto();
    }
}

