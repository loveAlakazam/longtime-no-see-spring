package spring_study.springmvc.boards;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring_study.springmvc.domain.Board;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // 테스트종료 데이터 초기화
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 임베디드 데이터베이스 사용
public class BoardRepositoryTest {
    // 공용 테스트 데이터
    private BoardRepository boardRepository;

    @Autowired
    public BoardRepositoryTest( BoardRepository boardRepository ) {
        this.boardRepository = boardRepository;
    }


    @Test
    @DisplayName( "게시글 생성" )
    public void test_save() {
        // given
        Board board = new Board();
        board.setTitle( "테스트 게시글 제목" );
        board.setContent( "테스트 게시글 내용" );
        board.setPassword( "1234" );
        board.setAuthorName( "테스트 작성자" );

        // when
        Board savedBoard = boardRepository.save( board );

        // then
        assertThat( savedBoard ).isNotNull();
        assertThat( savedBoard.getId() ).isEqualTo( 1 );
    }

    @Test
    @DisplayName( "비어있을 경우 전체조회" )
    public void test_findAll_1() {
        // given
        // when
        List<Board> boards = boardRepository.findAll();

        // then
        assertThat( boards.size() ).isEqualTo( 0 );
    }
    @Test
    @DisplayName( "데이터가 들어있는 경우 전체조회" )
    public void test_findAll_2() {
        // given
        for(int i=0; i< 10; i++) {
            Board board = new Board();
            board.setTitle( "테스트 게시글 제목" + (i+1) );
            board.setContent( "테스트 게시글 내용" + (i+1) );
            board.setPassword( "1234" );
            board.setAuthorName( "테스트 작성자" + (i+1) );
            boardRepository.save(board);
        }

        // when
        List<Board> boards = boardRepository.findAll();

        // then
        assertThat( boards.size() ).isEqualTo( 10 );
    }
    @Test
    public void test_findById() {
        // given
        Board board = new Board();
        board.setTitle( "테스트 게시글 제목" );
        board.setContent( "테스트 게시글 내용" );
        board.setPassword( "1234" );
        board.setAuthorName( "테스트 작성자" );
        Board savedBoard = boardRepository.save( board );

        // when
        Optional<Board> result =boardRepository.findById( 1L );

        // then
        assertThat( result.get() ).isNotNull();
    }


    @BeforeEach
    void beforeEach() {
        // 테스트 이전 데이터베이스 데이터 삭제
        boardRepository.deleteAll();
    }
    @AfterEach
    void afterEach() {
        // 테스트 이후 데이터베이스 데이터 삭제
        boardRepository.deleteAll();
    }


}
