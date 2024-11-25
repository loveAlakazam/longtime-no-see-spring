package spring_study.springmvc.boards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_study.springmvc.boards.dto.CreateBoardRequestDto;
import spring_study.springmvc.boards.dto.CreateBoardResponseDto;
import spring_study.springmvc.boards.dto.GetBoardResponseDto;
import spring_study.springmvc.domain.Board;

import java.util.List;
import java.util.Optional;


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

    @GetMapping("board/{id}")
    @ResponseBody
    public GetBoardResponseDto getBoardById(@PathVariable Long id) {
        return boardService.getById( id );
    }
//    @PatchMapping("boards/:id")
}

