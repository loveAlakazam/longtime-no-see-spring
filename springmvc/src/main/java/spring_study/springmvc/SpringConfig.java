package spring_study.springmvc;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_study.springmvc.repository.JdbcTemplateMemberRepository;
import spring_study.springmvc.repository.JpaMemberRepository;
import spring_study.springmvc.repository.MemberRepository;
import spring_study.springmvc.repository.MemoryMemberRepository;
import spring_study.springmvc.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }


//    @PersistenceContext
//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }


    @Autowired
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {

//        return new MemberService(memberRepository())
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//
//        return new MemoryMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
