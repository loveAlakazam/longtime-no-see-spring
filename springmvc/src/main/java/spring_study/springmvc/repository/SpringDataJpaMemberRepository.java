package spring_study.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_study.springmvc.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
