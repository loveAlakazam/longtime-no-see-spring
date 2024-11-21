package spring_study.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring_study.springmvc.domain.Member;
import spring_study.springmvc.service.MemberService;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 멤버생성 폼UI를 리턴
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    // 신규멤버 생성후 홈화면 리턴
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";
    }
    // 회원목록 리턴
    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
