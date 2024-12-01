package spring_study.springmvc.boards;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_study.springmvc.boards.dto.*;
import spring_study.springmvc.boards.errors.PasswordMismatchException;
import spring_study.springmvc.commons.NotFoundException;
import spring_study.springmvc.domain.Board;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시판 생성
    public CreateBoardResponseDto create( CreateBoardRequestDto dto ) {
        try {
            // 등록
            // dto -> entity
            Board board = Board.builder()
                    .title( dto.getTitle() )
                    .content( dto.getContent() )
                    .password( dto.getPassword() )
                    .authorName( dto.getAuthorName() ).build();
            // repository
            boardRepository.save( board );

            // entity -> dto
            return CreateBoardResponseDto.builder()
                    .id( board.getId() )
                    .title( board.getTitle() )
                    .content( board.getContent() )
                    .createDate( board.getCreateDate() )
                    .authorName( board.getAuthorName() ).build();

        } catch ( Exception e ) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public List< GetBoardResponseDto > getAll() {
        try {
            // repository
            List<Board> boards = boardRepository.findAll();

            // entity -> dto
            return boards.stream().map( board -> GetBoardResponseDto.builder()
                    .id( board.getId() )
                    .title( board.getTitle() )
                    .content( board.getContent() )
                    .createDate( board.getCreateDate() )
                    .authorName( board.getAuthorName() )
                    .build()
            ).collect( Collectors.toList() );

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public GetBoardResponseDto getById(long id) {
        // repository
        Optional<Board>  result = boardRepository.findById( id );

        // entity -> dto
        return result.map(
            board -> GetBoardResponseDto.builder().id( board.getId() ).title( board.getTitle() ).content( board.getContent() ).createDate( board.getCreateDate() ).authorName( board.getAuthorName() ).build()
        ).orElse( null );
    }

    public boolean isRightPassword (long id, String inputPassword) throws PasswordMismatchException {
        // 원본 비밀번호 조회
        String realBoardPassword = boardRepository.findById( id ).map( Board::getPassword ).orElseThrow( PasswordMismatchException::new );

        // 입력 비밀번호와 원본 비밀번호 일치확인
        return realBoardPassword.equals( inputPassword );
    }


    public UpdateBoardResponseDto updateBoard (long id, UpdateBoardRequestDto dto) throws Exception {
        try {
            // repository
            // 게시글 조회
            Board board = boardRepository.findById( id ).orElseThrow(() -> new NotFoundException("존재하지 않은 게시글 입니다.") );

            // 게시글 수정
            if ( dto.getTitle() != null ) {
                // 제목 수정
                board.setTitle( dto.getTitle() );
            }
            if ( dto.getContent() != null ) {
                // 내용 수정
                board.setContent( dto.getContent() );
            }
            if (dto.getPassword() != null) {
                // 비밀번호 수정
                board.setPassword( dto.getPassword() );
            }
            if(dto.getAuthorName() != null) {
                // 작성자명 수정
                board.setAuthorName( dto.getAuthorName() );
            }
            boardRepository.save( board );

            // entity -> dto
            return UpdateBoardResponseDto.builder()
                    .id( board.getId() )
                    .title( board.getTitle() )
                    .content( board.getContent() )
                    .authorName( board.getAuthorName() ).build();
        } catch( Exception e) {
            if(e instanceof NotFoundException) {
                throw e;
            }
            throw new Exception(e.getMessage());
        }
    }

}
