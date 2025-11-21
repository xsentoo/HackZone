package com.uphf.HackZone.Controller;


import com.uphf.HackZone.Entity.UserEntity;
import com.uphf.HackZone.Repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {
private final UserRepository userRepository;
public HomeController(UserRepository userRepository) {
    this.userRepository = userRepository;
}
@GetMapping("/Home")
    public String home(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String userMail = auth.getName();
    Optional<UserEntity> user = userRepository.findByUserMail(userMail);
    if (user.isPresent()) {
        model.addAttribute("user" , user.get());
        return "Home";
    }
    return "redirect:/Auth/login";
}
}
