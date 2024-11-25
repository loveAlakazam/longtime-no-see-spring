package spring_study.springmvc.boards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import spring_study.springmvc.boards.dto.CreateBoardRequestDto;
import spring_study.springmvc.boards.dto.CreateBoardResponseDto;
import spring_study.springmvc.boards.dto.GetBoardResponseDto;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("board")
    @ResponseBody
    public CreateBoardResponseDto create ( @RequestBody()CreateBoardRequestDto requestDto ) {
        return  boardService.create( requestDto );
    }


    @GetMapping("boards")
    @ResponseBody
    public List<GetBoardResponseDto> getAllBoards () {
        return boardService.getAll();
    }
}

