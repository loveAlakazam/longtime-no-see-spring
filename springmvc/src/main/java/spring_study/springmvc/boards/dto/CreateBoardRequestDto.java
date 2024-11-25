package spring_study.springmvc.boards.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
public class CreateBoardRequestDto {
    private String title;
    private String content;
    private String password;
    private String authorName;

    // 생성자
    @Builder
    public CreateBoardRequestDto(String title, String content, String password, String authorName) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.authorName = authorName;
    }
}