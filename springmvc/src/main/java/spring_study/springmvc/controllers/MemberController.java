package spring_study.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spring_study.springmvc.service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
