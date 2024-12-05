package spring_study.springmvc.users.dto;

import jakarta.validation.constraints.NotEmpty;

public class LogInUserRequestDto {
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
