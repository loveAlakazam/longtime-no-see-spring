package spring_study.springmvc.boards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_study.springmvc.boards.dto.*;
import spring_study.springmvc.boards.errors.PasswordMismatchException;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("board")
    @ResponseBody
    public CreateBoardResponseDto create ( @RequestBody()CreateBoardRequestDto request ) {
        return  boardService.create( request );
    }


    @GetMapping("boards")
    @ResponseBody
    public List<GetBoardResponseDto> getAllBoards () {
        return boardService.getAll();
    }

    @GetMapping("board/{id}")
    @ResponseBody
    public GetBoardResponseDto getBoardById( @PathVariable long id )  {
        return boardService.getById( id );
    }
    @PatchMapping("boards/{id}")
    @ResponseBody
    public UpdateBoardResponseDto updateBoard( @PathVariable long id, @RequestBody UpdateBoardRequestDto request ) throws PasswordMismatchException {
        String inputPassword = request.getInputPassword();
        boolean matchedPassword = this.boardService.isRightPassword( id , inputPassword );
        if ( !matchedPassword ) {
            // 비밀번호 불일치하면 400번 예외처리.
            throw new PasswordMismatchException();
        }
        return new UpdateBoardResponseDto();
    }
}

