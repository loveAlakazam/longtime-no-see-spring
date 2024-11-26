package spring_study.springmvc.boards;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring_study.springmvc.domain.Board;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BoardRepositoryTest {

    // 공용 테스트 데이터
    @Autowired
    private BoardRepositoryImpl boardRepository;



    @Test
    @DisplayName( "게시글 생성" )
    public void 게시글_생성_테스트() {
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
    public void 게시글_전체조회_테스트_1() {
        // given
        // when
        List<Board> boards = boardRepository.findAll();

        // then
        assertThat( boards.size() ).isEqualTo( 0 );
    }
    @Test
    @DisplayName( "데이터가 들어있는 경우 전체조회" )
    public void 게시글_전체조회_테스트_2() {
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
    public void 게시글_단건조회_테스트() {
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

}
