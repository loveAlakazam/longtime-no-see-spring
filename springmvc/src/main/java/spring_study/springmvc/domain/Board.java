package spring_study.springmvc.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;



@Entity
@Table(name = "board")
@Data
public class Board {
    // 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // 아이디

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false)
    private String content; // 내용

    @Column(nullable = false)
    private String password; // 비밀번호

    @CreatedDate
    @Column(updatable = false)
    @Temporal( TemporalType.TIMESTAMP )
    private LocalDateTime createDate; // 작성날짜

    @Column(nullable = false)
    private String authorName; // 작성자명

    @Builder
    public Board(String title, String content, String password, String authorName) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.authorName = authorName;
    }

}
