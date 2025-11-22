package com.uphf.HackZone.Controller;

import com.uphf.HackZone.Entity.UserEntity;
import com.uphf.HackZone.Repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
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
        Optional<UserEntity> userOpt = userRepository.findByUserMail(userMail);

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            model.addAttribute("user", user);

            int score = user.getPoint();
            int nextLevelScore = 500;

            if ("int".equals(user.getLevel())) {
                nextLevelScore = 1500;
            } else if ("avan".equals(user.getLevel())) {
                nextLevelScore = 5000;
            }

            int percent = 0;
            if (nextLevelScore > 0) {
                percent = (int) ((score * 100.0) / nextLevelScore);
            }
            if (percent > 100) percent = 100;

            model.addAttribute("nextLevelScore", nextLevelScore);
            model.addAttribute("progressPercent", percent);

            List<UserEntity> leaderboard = userRepository.findTop10ByOrderByPointDesc();
            if (leaderboard.size() > 5) {
                leaderboard = leaderboard.subList(0, 5);
            }
            model.addAttribute("leaderboard", leaderboard);

            return "Home";
        }
        return "redirect:/Auth/login";
    }
}