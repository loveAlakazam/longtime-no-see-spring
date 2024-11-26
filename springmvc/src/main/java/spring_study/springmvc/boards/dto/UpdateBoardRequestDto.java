package spring_study.springmvc.boards.dto;
import lombok.Builder;
import lombok.Getter;


@Getter
public class UpdateBoardRequestDto  extends PasswordMatchRequestDto {
    // 필드
    private String title;
    private String

    // 생성자
    @Builder
    public UpdateBoardRequestDto( long id , String password , String title, String content, String authorName, String ) {
        super( id , password );
    }
}
