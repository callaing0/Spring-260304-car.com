package com.book.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {
	
	@GetMapping("/agree")
	public String showPrivacy() {
	    return "car/agree"; // 개인정보 동의 페이지 호출
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
        return "car/signup"; // 실제 회원가입 양식 페이지 호출
    }
}
