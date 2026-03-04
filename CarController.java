package com.book.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    @GetMapping("/")
    public String home() {
    	return "car/index"; // templates 폴더 안 car 폴더 안에 들어있는 index.html
    }

    @GetMapping("/login")
    public String login() {
        return "car/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "car/signup";
    }
}
