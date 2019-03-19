package com.example.socialnetwork.controller;


import com.example.socialnetwork.model.User;
import com.example.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String main(){
        return "login";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        return "redirect:/home";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login";
    }

}

