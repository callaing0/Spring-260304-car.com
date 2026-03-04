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
    
 // 비밀번호 수정 페이지로 이동
    @GetMapping("/mypage/edit")
    public String editPasswordForm(HttpSession session) {
        // 로그인 안 되어 있으면 로그인 페이지로
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "car/edit-password"; // templates/car/edit-password.html
    }

    // 비밀번호 수정 처리
    @PostMapping("/mypage/edit")
    public String updatePassword(String currentPassword, String newPassword, HttpSession session, org.springframework.ui.Model model) {
        // 1. 세션에서 현재 로그인한 유저 정보 가져오기
        CarMemberDTO sessionUser = (CarMemberDTO) session.getAttribute("user");
        
        if (sessionUser == null) return "redirect:/login";

        // 2. 현재 비밀번호가 맞는지 검사
        if (!sessionUser.getPassword().equals(currentPassword)) {
            model.addAttribute("error", "현재 비밀번호가 일치하지 않습니다.");
            return "car/edit-password"; // 틀리면 다시 수정 페이지로
        }

        // 3. 새 비밀번호 설정 및 DB 저장
        // sessionUser는 영속성 컨텍스트에 연결된 상태가 아닐 수 있으므로 리포지토리에서 다시 조회 권장
        CarMemberDTO dbUser = carmemberRepository.findByEmail(sessionUser.getEmail());
        dbUser.setPassword(newPassword);
        carmemberRepository.save(dbUser); // ID가 존재하므로 UPDATE 쿼리 실행됨

        // 4. 세션 정보 갱신 (비밀번호가 바뀌었으므로)
        session.setAttribute("user", dbUser);

        // 5. 성공 후 마이페이지로 리다이렉트 (성공 메시지 전달용 파라미터 추가)
        return "redirect:/mypage?success=true";
    }
}
