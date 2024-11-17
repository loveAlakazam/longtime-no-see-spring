package spring_study.springmvc.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import spring_study.springmvc.domain.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    // 공용 테스트 데이터
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 메서드 실행이 끝날 때마다
    // 다음테스트에 영향을 미치지 않도록 공용 데이터셋을 삭제.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName( "spring" );

        repository.save(member);
        Member result = repository.findById( member.getId() ).get();
        // org.junit.jupiter.api.Assertions
        Assertions.assertEquals( result ,  member );

        // org.assertj.core.api.Assertions.assertThat
        assertThat( member ).isEqualTo( result );
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("user1");
        repository.save( member1 );

        Member member2 = new Member();
        member2.setName("user1");
        repository.save( member2 );

        // findByName
        Member result = repository.findByName( "user1" ).get();
        assertThat( result ).isEqualTo( member1 );
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("user1");
        repository.save( member1 );

        Member member2 = new Member();
        member2.setName("user1");
        repository.save( member2 );

        List<Member> result = repository.findAll();
        assertThat( result.size() ).isEqualTo( 2 );
    }
}
