package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; //home.html이 호출이 된다.
    }

    @GetMapping(value="/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    
    
}
