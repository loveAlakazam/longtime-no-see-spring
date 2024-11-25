package spring_study.springmvc.boards.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@RequiredArgsConstructor
public class CreateBoardResponseDto {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createDate;

    @Deprecated
    private String authorName;


    @Builder
    public CreateBoardResponseDto( long id , String title , String content , LocalDateTime createDate , String authorName ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.authorName = authorName;
    }
}
