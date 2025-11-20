package com.uphf.HackZone.Controller;


import com.uphf.HackZone.Component.JwtUtil;
import com.uphf.HackZone.Entity.UserEntity;
import com.uphf.HackZone.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import java.util.Optional;

@Controller
@RequestMapping("/Auth")
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil JwtUtil;

    public AuthController(UserRepository userRepository , JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.JwtUtil = jwtUtil;
    }

    @GetMapping("/login")
    public String ShowLogin(){
        return "Login";
    }

    @PostMapping("/login")
    public String LoginUser(@RequestParam String userMail ,@RequestParam String userPWD , Model model){
        Optional<UserEntity> user = userRepository.findByUserMail(userMail);
        if (user.isPresent() && user.get().getUserPWD().equals(userPWD)) {
            String token = JwtUtil.generateToken(userMail);
            model.addAttribute("token", token);
            model.addAttribute("user",user.get());
            return "Home";
        }
        model.addAttribute("error", "Email ou mot de passe incorrect");
        return "Login";
    }
}
