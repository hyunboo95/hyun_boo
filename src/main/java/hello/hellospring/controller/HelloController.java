package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    
    // 템플릿 엔진 방식
    @GetMapping("hello") //hello라는 매핑주소가 get방식을 통해 들어오면 해당 메서드가 실행
    public String hello(Model model) {
        model.addAttribute("data","hello");
        return "hello"; // templetes의 hello를 찾아서 랜더링 해라
    }

    // MVC 방식
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) { //외부에서 파라미터를 받아옴, RequestParam의 required는 default가 true이므로 파라미터값이 필수이다.
        model.addAttribute("name",name); //파라미터로 넘어온 name을 넘긴다.
        return "hello-template"; //hello-template으로 보낸다.
    }

    // API 방식
    @GetMapping("hello-string")
    @ResponseBody // 응답 body부에 (여기선 http body) 이 데이터를 직접 넣어 주겠다는 의미.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 이 데이터를 그대로 내려준다.
    }

    //API 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello { // static 클래스로 만들면 여기 HelloController.java안에서 사용할 수 있다.
        private String name;
        // 자바 빈 표준양식 : getter, setter
        // private은 이 static 클래스내에서만 사용가능하므로 외부에서 사용할 때에는
        // 아래의 getter setter 메서드를 이용해야 함.
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;    
        }
    }

}
