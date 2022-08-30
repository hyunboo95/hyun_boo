package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller //컨트롤러라는 애노테이션을 보고, 스프링이 뜰 때, MemberController라는 객체를 생성해서 가지고 있다.
            // ** 이러한 과정을 스프링 컨테이너에서 스프링 빈이 관리된다고 한다. **
public class MemberController {
    
    //스프링 컨테이너에 등록은 딱 하나만 하면 된다. 그 다음부터는 공통으로 가져다 쓰는 것.
    private final MemberService memberService;

    @Autowired  // 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. :: 객체 의존관계를 외부에서 넣어주는 것 -> DI :: @Autowired에 의해 스프링이 의존성을 주입.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 등록

    @GetMapping(value = "/members/new") // Get방식 : url을 직접 입력하는 방식 :: 조회할 때 주로 사용
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new") // Post 방식 : 데이터를 전달할 때 사용
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        
        memberService.join(member);
        return "redirect:/";
    }

    // 회원 조회
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
        
}
