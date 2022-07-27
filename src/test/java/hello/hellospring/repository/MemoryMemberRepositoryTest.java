package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

/*public*/ class MemoryMemberRepositoryTest { //여기서만 사용할 것이기 때문에 public으로 할 필요가 없다.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

       Member result = repository.findById(member.getId()).get(); // optional에서 값을 꺼낼때는 get()으로 꺼낸다 (하지만 그리 좋은 방법은 아님)

       // System.out.println("result = " + (result == member)); 이렇게 출력되는 글자로 확인하는 방법도 있지만 아래방법을 쓰자
       //Assertions.assertEquals(member, result); // (기대하는것, 결과) 순으로 작성 :: 다르면 오류가 난다.
       assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        assertThat(result).isEqualTo(member1);
    }
}
