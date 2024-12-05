package spring_study.springmvc.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_study.springmvc.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save (User user); // 회원 생성
    Optional<User> findByEmail (String email); // 유저 조회 (이메일)
}
