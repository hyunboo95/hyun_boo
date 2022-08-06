package hello.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

    
    
    
}
