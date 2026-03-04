package com.book.Repository;

import com.book.Entity.CarMemberDTO; // Member 대신 CarMemberDTO 임포트
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMemberRepository extends JpaRepository<CarMemberDTO, Long> {
    // 인터페이스(interface)로 선언해야 스프링 JPA가 자동으로 구현체를 만들어줍니다.
}
