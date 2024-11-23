package spring_study.springmvc.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
public class Board {
    // 필드
    @Id
    @GeneratedValue()
    private double id; // 아이디
    private String title; // 제목
    private String content; // 내용
    private String password; // 비밀번호
    private Date createDate; // 작성날짜

    @Deprecated
    private String authorName; // 작성자명

    // 생성자
    public Board() {}
    public Board(String title, String content, String password, String authorName ) {
        this();
        this.title = title;
        this.content = content;
        this.password = password;

        // 추후 deprecated 예정.
        this.authorName = authorName;
    }

    public Board(String title, String content, String password, String authorName , double id, Date createDate) {
        this(title, content, password, authorName);
        this.id = id;
        this.createDate = createDate;
    }

}
