package spring_study.springmvc.boards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@RequiredArgsConstructor
public class GetBoardResponseDto {

    @JsonProperty
    private long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private String content;

    @JsonProperty
    private LocalDateTime createDate;

    @Deprecated
    @JsonProperty
    private String authorName;

    @Builder
    public GetBoardResponseDto( long id , String title , String content , LocalDateTime createDate , String authorName ) {
        this();
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.authorName = authorName;
    }
}
