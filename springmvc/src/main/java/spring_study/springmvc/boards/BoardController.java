package spring_study.springmvc.boards;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_study.springmvc.boards.dto.*;
import spring_study.springmvc.boards.errors.PasswordMismatchException;
import spring_study.springmvc.commons.NotFoundException;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("board")
    @ResponseBody
    @ResponseStatus( HttpStatus.CREATED)
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
    public GetBoardResponseDto getBoardById( @PathVariable long id ) throws NotFoundException {
        try{
            return boardService.getById( id );
        } catch(Exception e) {
            throw e;
        }
    }
    @PatchMapping("board/{id}")
    @ResponseBody
    public UpdateBoardResponseDto updateBoard( @PathVariable long id, @RequestBody() UpdateBoardRequestDto request ) throws Exception {
        try {
            // id에 부합한 게시글이 존재하는지 확인
            GetBoardResponseDto board = this.boardService.getById( id );

            // 입력비밀번호와 실제비밀번호 일치여부 확인
            String inputPassword = request.getInputPassword();
            boolean matchedPassword = this.boardService.isRightPassword( id , inputPassword );
            if ( !matchedPassword ) {
                // 비밀번호 불일치하면 400번 예외처리.
                throw new PasswordMismatchException();
            }

            // 게시글 수정
            return this.boardService.updateBoard( id , request );
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("board/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable long id, @RequestBody() DeleteBoardRequestDto request ) throws PasswordMismatchException, NotFoundException {
        try {
            // id에 부합한 게시글이 존재하는지 확인
            GetBoardResponseDto board = this.boardService.getById( id );

            // 입력비밀번호와 실제비밀번호 일치여부 확인
            String inputPassword = request.getInputPassword();
            boolean matchedPassword = this.boardService.isRightPassword( id , inputPassword );
            if ( !matchedPassword ) {
                // 비밀번호 불일치하면 400번 예외처리.
                throw new PasswordMismatchException();
            }

            // 게시글 삭제
            this.boardService.deleteById(id);
        } catch(Exception e) {
            throw e;
        }

    }
}

