package spring_study.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello"; // resources/templates/hello.html 을 리턴해서 렌더링.
    }
    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam(value = "name") String name,  Model model) {
        model.addAttribute("name", name);
        return "hello-mvc";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        // 단순문자열이면 StringConverter로 응답데이터를 만듦.
        return "hello" + name;
    }


    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        // HttpMessageConverter 로 객체를 json방식으로 응답데이터를 만듦
        return hello; // 객체를 리턴 => 객체인경우에는 JSON으로 데이터를 만들어서 HTTP 응답으로 리턴
    }
    static class Hello {
        private String name;

        public String getName( ) {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }
    }
}
