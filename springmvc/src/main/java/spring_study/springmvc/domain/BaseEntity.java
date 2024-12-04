package spring_study.springmvc.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@EntityListeners( AuditingEntityListener.class )
public abstract class BaseEntity {
    // @CreatedDate
    //  Entity 가 생성될 때마다 자동으로 현재날짜와 시간을 저장하는 어노테이션
    //  이 어노테이션이 적용된 필드는 Entity가 생성될때마다 데이터베이스에 현재날짜와 시간이 자동으로 저장된다.
    @CreatedDate
    protected LocalDateTime createDate;

    // @LastModifiedDate
    //  Entity 가 수정될 때 자동으로 현재 날짜와 시간을 저장하는 어노테이션
    //  이 어노테이션이 적용된 필드는 Entity가 수정될때마다 데이터베이스에 현재 날짜와 시간이 자동으로 저장된다.
    @LastModifiedDate
    protected LocalDate updateDate;
}
