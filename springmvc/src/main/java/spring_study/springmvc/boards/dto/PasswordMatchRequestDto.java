package spring_study.springmvc.boards.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public abstract class PasswordMatchRequestDto {
    private long id; // 게시글 아이디
    private String inputPassword; // 입력 패스워드

    public PasswordMatchRequestDto( long id , String inputPassword ) {
        this.id = id;
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
