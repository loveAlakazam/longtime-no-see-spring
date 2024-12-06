package spring_study.springmvc.boards.dto;
import lombok.Builder;
import lombok.Getter;


@Getter
public class UpdateBoardRequestDto  extends PasswordMatchRequestDto {
    // 필드
    // inputPassword; // 입력 비밀번호
    private String title; // 제목
    private String content; // 내용
    private String password; // (변경예정) 비밀번호

    @Deprecated
    private String authorName; // 작성자명


    // 생성자
    public UpdateBoardRequestDto(String inputPassword ) {
        super( inputPassword );
    }

    @Builder
    public UpdateBoardRequestDto( String inputPassword, String title, String content, String password, String authorName) {
        this(inputPassword);
        this.title = title;
        this.content = content;
        this.password = password;
        this.authorName = authorName;
    }
}
