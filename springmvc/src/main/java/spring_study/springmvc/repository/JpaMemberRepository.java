package spring_study.springmvc.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import spring_study.springmvc.domain.Member;

import java.util.List;
import java.util.Optional;

// jpa는 모든 데이터 변경이 @Transactional 안에 실행...
@Transactional
public class JpaMemberRepository implements MemberRepository{
    // jpa는 entityManager로 동작한다.
    private final EntityManager em;
    public JpaMemberRepository (EntityManager em) {
        this.em = em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m fromMember m", Member.class).getResultList();

    }
}
