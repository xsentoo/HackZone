package com.uphf.HackZone.Controller;


import com.uphf.HackZone.Component.JwtUtil;
import com.uphf.HackZone.Entity.UserEntity;
import com.uphf.HackZone.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository , JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.JwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String ShowLogin(){
        return "Login";
    }

    @PostMapping("/login")
    public String LoginUser(@RequestParam String userMail , @RequestParam String userPWD , Model model, HttpServletResponse response){
        Optional<UserEntity> user = userRepository.findByUserMail(userMail);
        // il faut ajouter BCryptPasswordEncoder sinon si ya un ijection sql c mort parce que userPWD est visible
        if (user.isPresent() && passwordEncoder.matches(userPWD,user.get().getUserPWD())) {
            String token = JwtUtil.generateToken(userMail);

            // je cree un cookie pour stocker le tocken si on cree pas un cookie si utilisateur va dans un autre page token sera perdu
            Cookie cookie = new Cookie("Authorization", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(60*60);

            response.addCookie(cookie);
            return "redirect:/Home";
        }
        model.addAttribute("error", "Email ou mot de passe incorrect");
        return "Login";
    }

    @GetMapping("/register")
    public String ShowRegister(){
        return "Register";

    }
    @PostMapping("/register")
    public String registerUser(@RequestParam String userName ,@RequestParam String userMail , @RequestParam String userPWD, Model model){
      if(userRepository.findByUserMail(userMail).isPresent()){
          model.addAttribute("error", "Cet email est deja utilisé !");
          return "Register";
      }

      //ici on peux pas utiliser Autowired parce que userEntity c'est pas un bean ca veux dire qui gere pas par spring
      UserEntity newUser = new UserEntity();
      newUser.setUserName(userName);
      newUser.setUserMail(userMail);

      String encodedPassword = passwordEncoder.encode(userPWD);
      newUser.setUserPWD(encodedPassword);

      newUser.setLevel("deb");
      newUser.setUserBadge("Novice");
      newUser.setUserDate(java.time.LocalDate.now());

      userRepository.save(newUser);

  return "redirect:/Auth/login";
    }
}
