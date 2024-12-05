package spring_study.springmvc.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_study.springmvc.users.dto.JoinUserRequestDto;
import spring_study.springmvc.users.dto.JoinUserResponseDto;
import spring_study.springmvc.users.dto.LogInUserRequestDto;
import spring_study.springmvc.users.dto.LogInUserResponseDto;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("user")
    @ResponseBody
    @ResponseStatus( HttpStatus.CREATED)
    public JoinUserResponseDto join ( @RequestBody() JoinUserRequestDto request ) {

        return new JoinUserResponseDto();
    }

    @PostMapping("log-in")
    @ResponseBody
    public LogInUserResponseDto logIn ( @RequestBody()LogInUserRequestDto request) {
        return new LogInUserResponseDto();
    }

    @GetMapping("log-out")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logOut() {

    }


}
