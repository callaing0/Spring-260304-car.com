package com.book.Repository;

import com.book.Entity.CarMemberDTO; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMemberRepository extends JpaRepository<CarMemberDTO, Long> {
    
    // 이메일로 회원 정보를 조회하는 기능을 추가합니다.
    // 메서드 이름 규칙에 따라 Spring Data JPA가 자동으로 SQL(SELECT * FROM carmember WHERE email = ?)을 생성합니다.
    CarMemberDTO findByEmail(String email);
    
}
