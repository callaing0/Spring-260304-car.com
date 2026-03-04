package com.book.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.book.Entity.CarMemberDTO; 
import com.book.Repository.CarMemberRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CarController {
	
    @Autowired
    private CarMemberRepository carmemberRepository;

    @GetMapping("/")
    public String home() {
        return "car/index"; 
    }

    // ★ [추가] 로그인 화면을 보여주는 메서드
    @GetMapping("/login")
    public String loginForm() {
        return "car/login"; // src/main/resources/templates/car/login.html 호출
    }

    // ★ [추가] 회원가입 양식 화면을 보여주는 메서드
    @GetMapping("/signup")
    public String signupForm() {
        return "car/signup"; // src/main/resources/templates/car/signup.html 호출
    }

    @GetMapping("/agree")
    public String showPrivacy() {
        return "car/agree"; 
    }

    // 회원가입 처리
    @PostMapping("/signup") 
    public String register(CarMemberDTO member) {
        carmemberRepository.save(member);
        return "redirect:/login"; // 가입 후 로그인 페이지로
    }

    // 로그인 처리
    @PostMapping("/login")
    public String loginProcess(String email, String password, HttpSession session) {
        CarMemberDTO user = carmemberRepository.findByEmail(email); 
        
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user); 
            return "redirect:/"; // 성공 시 메인으로 튕겨주기!
        }
        return "redirect:/login?error"; // 실패 시 에러 표시와 함께 리다이렉트
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/";
    }
    
    @GetMapping("/mypage")
    public String showMyPage(HttpSession session, org.springframework.ui.Model model) {
        CarMemberDTO user = (CarMemberDTO) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("member", user);
        return "car/mypage"; 
    }
}
