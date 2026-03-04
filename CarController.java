package com.book.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.book.Entity.CarMemberDTO; 
import com.book.Repository.CarMemberRepository;

@Controller
public class CarController {
	
    @Autowired
    private CarMemberRepository carmemberRepository;

    // 회원가입 처리 (signup.html의 action과 맞춤)
    @PostMapping("/signup") 
    public String register(CarMemberDTO member) { // Member 대신 CarMemberDTO 사용
        // 1. DB에 회원 정보 저장 (오타 수정: caemember -> carmember)
        carmemberRepository.save(member);
        
        // 2. 가입 완료 후 로그인 페이지로 이동
        return "redirect:/login";
    }
    
    @GetMapping("/agree")
    public String showPrivacy() {
        return "car/agree"; 
    }

    @GetMapping("/")
    public String home() {
        return "car/index"; 
    }

    @GetMapping("/login")
    public String login() {
        return "car/login";
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "car/signup"; 
    }
}
