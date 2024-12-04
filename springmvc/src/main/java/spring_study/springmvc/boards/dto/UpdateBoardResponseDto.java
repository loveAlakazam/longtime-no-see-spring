package spring_study.springmvc.boards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Getter
@Setter
public class UpdateBoardResponseDto {
    @JsonProperty
    private long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private String content;

    @Deprecated
    @JsonProperty
    private String authorName;

    // 생성자
    public UpdateBoardResponseDto(){}
    @Builder
    public UpdateBoardResponseDto(long id, String title, String content, String authorName) {
        this();
        this.id = id;
        this.title = title;
        this.content = content;

        this.authorName = authorName;
    }
}
