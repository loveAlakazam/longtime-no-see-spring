package spring_study.springmvc.boards.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public abstract class PasswordMatchRequestDto {
    private String inputPassword; // 입력 패스워드

    public PasswordMatchRequestDto(  String inputPassword ) {
        this.inputPassword = inputPassword;
    }
}

//@Data
//public class PasswordMatchRequestDto {
//    private long id;
//    private String password;
//
//    @Builder
//    public PasswordMatchRequestDto( long id, String password) {
//        this.id = id;
//        this.password = password;
//    }
//
//}
