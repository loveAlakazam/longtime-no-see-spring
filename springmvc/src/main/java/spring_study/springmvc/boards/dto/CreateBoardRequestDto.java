package spring_study.springmvc.boards.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateBoardRequestDto {
    private String title;
    private String content;
    private String password;
    private String authorName;

    public CreateBoardRequestDto(){}
    public CreateBoardRequestDto(String title, String content, String password, String authorName) {
        this();
        this.title = title;
        this.content = content;
        this.password = password;
        this.authorName = authorName;
    }
}