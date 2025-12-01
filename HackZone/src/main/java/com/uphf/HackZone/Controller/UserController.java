package com.uphf.HackZone.Controller;

import com.uphf.HackZone.Entity.UserEntity;
import com.uphf.HackZone.Repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{userId}")
    public String getUserName(@PathVariable int userId){
        Optional<UserEntity> user = userRepository.findById(userId);
        return user.map(UserEntity::getUserName).orElse("user not found");
    }
}