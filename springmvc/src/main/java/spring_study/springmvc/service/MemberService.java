package spring_study.springmvc.service;

import org.springframework.stereotype.Service;
import spring_study.springmvc.domain.Member;
import spring_study.springmvc.repository.MemberRepository;
import spring_study.springmvc.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * <p>
     * - 같은 이름의 회원이 있으면 안된다.
     */
    public Long join( Member member ) {
        validateDuplicateMember( member );// 중복회원검증
        memberRepository.save( member );
        return member.getId();

    }
    public void validateDuplicateMember( Member member ) {
        memberRepository.findByName( member.getName() ).ifPresent(
            m -> {
                throw new IllegalStateException( "이미 존재하는 회원입니다." );
            }
        );
    }
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById( memberId );
    }
}
