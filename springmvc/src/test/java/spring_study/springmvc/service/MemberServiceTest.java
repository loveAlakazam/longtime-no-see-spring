package spring_study.springmvc.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import spring_study.springmvc.domain.Member;
import spring_study.springmvc.repository.MemberRepository;
import spring_study.springmvc.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService ;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService( memberRepository );
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
//    @Commit // 테스트디비에는 원래는 롤백하지만 해당 어노테이션은 디비에 데이터로우를 남긴다
    void 회원가입( ) {
        // given
        Member member = new Member();
        member.setName( "spring" );

        // when
        Long saveId = memberService.join( member );

        // then
        Member findMember = memberService.findOne( saveId ).get();
        assertThat( member.getName() ).isEqualTo( findMember.getName() );
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member originMember = new Member();
        originMember.setName( "spring" );


        Member duplicatedMember = new Member();
        duplicatedMember.setName( "spring" );

        // when
        memberService.join(originMember);
        IllegalStateException exception = assertThrows( IllegalStateException.class , ( ) -> memberService.join( duplicatedMember ) );
        assertThat( exception.getMessage() ).isEqualTo( "이미 존재하는 회원입니다." );
//        try {
//            memberService.join(duplicatedMember);
//            fail("예외가 발생해야 합니다.");
//        } catch(IllegalStateException e) {
//            assertThat( e.getMessage() ).isEqualTo( "이미 존재하는 회원입니다." );
//        }
        // then
    }

    @Test
    void validateDuplicateMember( ) {
    }

    @Test
    void findMembers( ) {
    }

    @Test
    void findOne( ) {
    }
}