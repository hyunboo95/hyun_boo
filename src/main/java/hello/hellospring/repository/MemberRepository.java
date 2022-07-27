package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;

public interface MemberRepository { // 멤버 저장소 (리포지토리)
    Member save(Member member); // 저장소에 저장이 된다.
    Optional<Member> findById(Long id); // Optional은 자바8에 생긴 기능, findById나 findByName를 하면서 null이 생길 경우를 대비해 Optional로 감싸준다.
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 지금까지 저장된 모든 회원리스트를 반환해준다.
}
