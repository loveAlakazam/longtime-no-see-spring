package spring_study.springmvc.users.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class JoinUserResponseDto {
    private long id;
    private String email;
    private String name;
    private LocalDateTime createDate;

    @Builder
    public JoinUserResponseDto(long id, String email, String name, LocalDateTime createDate) {
        this();
        this.id = id;
        this.email = email;
        this.name = name;
        this.createDate = createDate;
    }

    public JoinUserResponseDto( ) {

    }
}
