package spring_study.springmvc.boards.dto;

import lombok.Data;
import lombok.Getter;


@Getter
public class CreateBoardResponseDto {
    private double id;
    private String title;
    private String content;
    private String createDate;

    @Deprecated
    private String authorName;
}
