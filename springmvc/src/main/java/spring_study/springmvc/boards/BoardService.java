package spring_study.springmvc.boards;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_study.springmvc.boards.dto.CreateBoardRequestDto;
import spring_study.springmvc.boards.dto.CreateBoardResponseDto;
import spring_study.springmvc.boards.dto.GetBoardResponseDto;
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

    public boolean isRightPassword (long id, String inputPassword) {
        // repository
        String realBoardPassword = String.valueOf( boardRepository.findById( id ).map( Board::getPassword ) );
        return realBoardPassword.equals( inputPassword );
    }


}
