package spring_study.springmvc.boards.dto;

import lombok.Builder;
import lombok.Data;

// 게시글 수정, 삭제 에 활용.
public abstract class PasswordMatchRequestDto {
    private long id; // 게시글 아이디
    private String password; //

    public PasswordMatchRequestDto( long id , String password ) {
        this.id = id;
        this.password = password;
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
