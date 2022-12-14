package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

//@Service // 스프링이 올라올때 서비스를 인식하고 스프링 빈을 등록해줌.
public class MemberService {
    
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { // memberRepository를 외부에서 넣어주도록 바꿈 -> Dependency Injection
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복회원은 가입이 불가
        /* 
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        이렇게 Optional을 사용할 수도 있지만 
        */
        validateDuplicateMember(member); // 중복 회원 검증
        

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }); // 이렇게 더 깔끔하게 쓸 수 있다.
                //또한 이렇게 하나의 로직이 쭉 나오는 경우에는 하나의 메서드로 뽑는게 좋다.
                //리팩토링 관련 단축키 : ctrl + shift + r
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    

}
