package com.uphf.HackZone.Controller;

import com.uphf.HackZone.Entity.AttackEntity;
import com.uphf.HackZone.Repository.AttackRepository;
import com.uphf.HackZone.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class LearningController {

    private final AttackRepository attackRepository;
    private final UserRepository userRepository;

    public LearningController(AttackRepository attackRepository, UserRepository userRepository) {
        this.attackRepository = attackRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/learn/{category}")
    public String showCategoryLevels(@PathVariable String category, Model model) {
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        userRepository.findByUserMail(userMail).ifPresent(u -> model.addAttribute("user", u));

        List<AttackEntity> challenges = attackRepository.findByCategory(category);

        model.addAttribute("categoryName", category);
        model.addAttribute("challenges", challenges);

        return "Learning/LevelSelect";
    }

    @GetMapping("/mission/{id}")
    public String showMissionPage(@PathVariable int id, Model model) {
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        userRepository.findByUserMail(userMail).ifPresent(u -> model.addAttribute("user", u));

        Optional<AttackEntity> attackOpt = attackRepository.findById(id);

        if (attackOpt.isPresent()) {
            model.addAttribute("challenge", attackOpt.get());
            return "Learning/MissionRoom";
        }
        return "redirect:/Home";
    }
}