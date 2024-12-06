package spring_study.springmvc.users.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class JoinUserRequestDto {

    @Email
    private String email;
    @Size(min = 4, max = 10)
    @Pattern( regexp = "^[a-z0-9]+$" )
    private String name;

    @Size(min =8, max=15)
    @Pattern(regexp = "^[a-zA-z0-9]+$")
    private String password;

    @Builder
    public JoinUserRequestDto(String email, String name, String password) {
        this();
        this.email = email;
        this.password = password;
        this.name = name;
    }

}
