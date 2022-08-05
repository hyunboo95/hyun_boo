package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 콜백 메서드 : 밑의 메서드가 실행이 끝날 때마다 동작이 실행.
    public void afterEach() {
        memberRepository.clearStore(); // 테스트가 하나 끝날 때마다 리포지토리를 지운다.
    }


    @Test
    void testFindMembers() {

    }

    @Test
    void testFindOne() {

    }

    @Test
    void testJoin() { // 정상 flow
        //given : 주어진 상황
        Member member = new Member();
        member.setName("hello");

        //when : 실행 했을 때,
        Long saveId = memberService.join(member);

        //then : 이러한 결과가 나와야 한다. (검증부분)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void memberDupException() {
        //given : 주어진 상황
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        //when : 실행 했을 때,
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            //TODO: handle exception
            //정상적으로 실행 되었을 때,
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
        


        //then : 이러한 결과가 나와야 한다. (검증부분)

    }
}
