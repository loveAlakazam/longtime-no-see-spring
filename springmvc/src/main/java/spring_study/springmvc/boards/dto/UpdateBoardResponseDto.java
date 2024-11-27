package spring_study.springmvc.boards.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class UpdateBoardResponseDto {
    private String title;
    private String content;
    private String password;
    private String authorName;

    // 생성자
    @Builder
    public UpdateBoardResponseDto(String title, String content, String password, String authorName) {
        this();
        this.title = title;
        this.content = content;
        this.password = password;
        this.authorName = authorName;
    }
}
