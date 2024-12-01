package spring_study.springmvc.boards;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import spring_study.springmvc.boards.dto.CreateBoardRequestDto;
import spring_study.springmvc.boards.dto.CreateBoardResponseDto;
import spring_study.springmvc.boards.dto.DeleteBoardRequestDto;
import spring_study.springmvc.boards.dto.UpdateBoardRequestDto;
import spring_study.springmvc.domain.Board;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @SpringBootTest
 * - 전체 애플리케이션 컨텍스트가 필요할 때
 * - 애플리케이션의 모든 빈과 서비스, 컨트롤러 등이 올바르게 동작하는지 종합적으로 테스트하고 싶을 때
 * - 실제 데이터베이스 연결이나 외부시스템 통합을 포함한 테스트가 필요할 때
 *
 *
 * @WebMvcTest
 * 컨트롤러 레이어에 집중된 테스트를 위한 어노테이션
 * 테스트 대상과 관련된 웹 계층의 빈(bean)들만 로드되므로 다른레이어의 컴포넌트는 모킹해야한다.
 *
 * - 컨트롤러만을 대상으로 경량화된 테스트가 필요할 때
 * - 실제 데이터베이스의 접근이나 스프링 컨테이너의 다른부분 없이 HTTP 요청과 응답에 대한 테스트를 수행하고자 할 때
 * - 컨트롤러가 예상대로 동작하는지 빠르게 검증하고 싶을 때
 *
 *
 * @link [참고자료]: https://medium.com/@hgbaek1128/springboottest%EC%99%80-webmvctest-54442cf9411d
 */
@AutoConfigureMockMvc // MockMvc에 Bean을 주입해준다.
@SpringBootTest
public class BoardControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ObjectMapper objectMapper;

//    @MockBean // BoardService 를 Mock 객체로 생성 => 데이터베이스 변화를 주지않음.
//    private BoardService boardService;
    @Autowired
    private BoardService boardService;

    @AfterEach
    public void tearDown() {
        // 각 테스트가 실행되기 전에 데이터베이스에 있는 데이터들을 삭제한다.
        this.boardRepository.deleteAll();
    }


    @DisplayName( "게시글 생성 테스트" )
    @Transactional
    @Test
    public void 신규_게시글을_생성한다() throws Exception {
        // given - 테스트 준비과정
        String title = "제목";
        String content = "내용";
        String password = "비밀번호";
        String authorName = "작성자 이름";
        CreateBoardRequestDto requestBody = CreateBoardRequestDto.builder()
                        .title( title )
                        .content( content )
                        .password( password )
                        .authorName( authorName ).build();


        // when - 테스트 수행
        ResultActions resultActions = mvc.perform( post( "/board" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( objectMapper.writeValueAsString( requestBody ) ) // HTTP Body 에 데이터를 넣는다
        );

        // then - 테스트 검증
        resultActions.andExpect( status().isCreated() );
        Assertions.assertThat( boardRepository.count() ).isEqualTo( 1 );

        // BoardService를 @Autowired대신 @MockBeans로 했을경우에만 해당됨.
//        CreateBoardResponseDto result = verify( boardService , times( 1 ) ).create( requestBody );
    }

    @DisplayName( "게시글 전체 조회 테스트" )
    @Test
    public void 게시글이_존재하지않으면_빈_리스트를_리턴한다() throws Exception {
        // when - 테스트 수행
        ResultActions resultActions = mvc.perform( get( "/boards" ).contentType( MediaType.APPLICATION_JSON ) );

        // then - 테스트 검증
        resultActions.andExpect(status().isOk());
        Assertions.assertThat( boardRepository.count() ).isEqualTo( 0 );
    }
    @Test
    public void 게시글의_전체를_리턴한다() throws Exception {
        // given
        // 데이터베이스에 10개의 게시글 데이터 저장
        for(int i = 0 ; i < 10; i++) {
            String title = "제목"+(i+1);
            String content = "내용"+(i+1);
            String password = "1234";
            String authorName = "작성자"+(i+1);
            boardService.create( new CreateBoardRequestDto( title , content , password , authorName ) );
        }

        // when
        ResultActions resultActions = mvc.perform( get( "/boards" ).contentType( MediaType.APPLICATION_JSON ) );

        // then
        resultActions.andExpect(status().isOk());
        Assertions.assertThat( boardRepository.count() ).isEqualTo( 10 );

    }
    @Test
    public void TBD_페이지네이션을_활용하여_맨첫번째_페이지를_리턴한다() {
        // TBD
    }
    @Test
    public void TBD_페이지네이션을_활용하여_두번째_페이지를_리턴한다() {
        // TBD
    }

    @DisplayName( "게시글 상세조회 테스트" )
    @Test
    public void 게시글_상세조회__게시글이_존재하지_않으면_NotFoundError를_예외로_한다() throws Exception {
        // when
        ResultActions resultActions = mvc.perform( get( "/board/1" )
                .contentType( MediaType.APPLICATION_JSON ) );
        // then
        resultActions.andExpect( status().isNotFound() );
    }
    @Test
    public void 게시글_상세정보를_리턴한다() throws Exception {
        // given
        // 게시글 데이터 1개를 데이터베이스에 미리 넣는다.
        String title = "제목";
        String content = "내용";
        String password = "1234";
        String authorName = "작성자";
        CreateBoardResponseDto newBoard = boardService.create( new CreateBoardRequestDto( title , content , password , authorName ) );

        // when
        ResultActions resultActions = mvc.perform( get( "/board/1" )
                .contentType( MediaType.APPLICATION_JSON ) );
        // then
        resultActions.andExpect(status().isOk())
                .andExpect( jsonPath( "$.id" ).value(newBoard.getId()) )
                .andExpect( jsonPath("$.title").value(newBoard.getTitle()) )
                .andExpect( jsonPath("$.content").value(newBoard.getContent()) )
                .andExpect( jsonPath("$.authorName").value(newBoard.getAuthorName()) )
        ;
    }

    @DisplayName( "게시글 수정 테스트" )
    @Test
    public void 게시글수정__게시글이_존재하지_않으면_NotFoundError를_예외로_한다() throws Exception {
        // given
        UpdateBoardRequestDto requestBody = UpdateBoardRequestDto
                .builder()
                .inputPassword( "1234" )
                .title("수정 제목")
                .content( "수정 내용" )
                .build();

        // when
        ResultActions resultActions = mvc.perform( patch( "/board/1" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( objectMapper.writeValueAsString( requestBody ) ) )
                ;
        // then
        resultActions.andExpect( status().isNotFound() );
    }
    @Test
    public void 게시글수정__게시글의_비밀번호가_일치하지않으면_PasswordMismatchError를_예외로_한다() throws Exception {
        // given
        // 게시글 데이터 1개를 데이터베이스에 미리 넣는다.
        String title = "제목";
        String content = "내용";
        String password = "1234";
        String authorName = "작성자";
        CreateBoardResponseDto board = boardService.create( new CreateBoardRequestDto( title , content , password , authorName ) );

        // 수정 요청 바디
        UpdateBoardRequestDto requestBody = UpdateBoardRequestDto
                .builder()
                .title("수정 제목")
                .inputPassword( "4321" ) // 비밀번호가 틀림
                .content( "수정 내용" )
                .build();

        // when
        ResultActions resultActions = mvc.perform( patch( "/board/"+ board.getId() )
                .contentType( MediaType.APPLICATION_JSON )
                .content( objectMapper.writeValueAsString( requestBody ) ) );
        // then
        resultActions.andExpect( status().isBadRequest() );
    }
    @Test
    public void 게시글을_수정한다() throws Exception {
        // given
        // 게시글 데이터 1개를 데이터베이스에 미리 넣는다.
        String title = "제목";
        String content = "내용";
        String password = "1234";
        String authorName = "작성자";
        CreateBoardResponseDto board = boardService.create( new CreateBoardRequestDto( title , content , password , authorName ) );

        // 수정 요청바디
        UpdateBoardRequestDto requestBody = UpdateBoardRequestDto
                .builder()
                .title("수정제목")
                .inputPassword( password ) // 비밀번호 일치
                .content( "수정내용" )
                .authorName( "수정작성자명" )
                .build();

        // when
        ResultActions resultActions = mvc.perform( patch( "/board/"+  board.getId() )
                .contentType( MediaType.APPLICATION_JSON )
                .content( objectMapper.writeValueAsString( requestBody ) ) );
        // then
        resultActions.andExpect( status().isOk() );
        Assertions.assertThat(requestBody.getTitle()).isEqualTo( "수정제목" );
        Assertions.assertThat(requestBody.getContent()).isEqualTo( "수정내용" );
        Assertions.assertThat(requestBody.getAuthorName()).isEqualTo( "수정작성자명" );
    }

    @DisplayName( "게시글 삭제 테스트" )
    @Test
    public void 게시글삭제__게시글이_존재하지_않으면_NotFoundError를_예외로_한다() throws Exception {
        // given
        String inputPassword = "1234";
        DeleteBoardRequestDto requestBody = new DeleteBoardRequestDto( inputPassword );

        // when
        ResultActions resultActions = mvc.perform( delete( "/board/1" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( objectMapper.writeValueAsString( requestBody )));

        // then
        resultActions.andExpect( status().isNotFound() );
    }
    @Test
    public void 게시글삭제__게시글의_비밀번호가_일치하지않으면_PasswordMismatchError를_예외로_한다() throws Exception {
        // given
        // 데이터 셋 셋팅
        String title = "제목";
        String content = "내용";
        String password = "1234";
        String authorName = "작성자";
        CreateBoardResponseDto board = boardService.create( new CreateBoardRequestDto( title , content , password , authorName ) );

        // 잘못된 비밀번호로 삭제요청으로 삭제요청 dto 생성
        String invalidInputPassword = "4321";
        DeleteBoardRequestDto requestBody = new DeleteBoardRequestDto( invalidInputPassword );

        // when
        ResultActions resultActions = mvc.perform( delete( "/board/1" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( objectMapper.writeValueAsString( requestBody )));

        // then
        resultActions.andExpect( status().isBadRequest() );
    }
    @Test
    public void 게시글을_삭제한다() throws Exception {
        // given
        // 데이터 셋 셋팅
        String title = "제목";
        String content = "내용";
        String password = "1234";
        String authorName = "작성자";
        CreateBoardResponseDto board = boardService.create( new CreateBoardRequestDto( title , content , password , authorName ) );

        // 삭제 요청 dto
        DeleteBoardRequestDto requestBody = new DeleteBoardRequestDto( password );

        // when
        ResultActions resultActions = mvc.perform( delete( "/board/1" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( objectMapper.writeValueAsString( requestBody )));

        // then
        resultActions.andExpect( status().isNoContent() );
    }
}
