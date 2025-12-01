package com.uphf.HackZone.Repository;

import com.uphf.HackZone.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserMail(String userMail);
    List<UserEntity> findTop10ByOrderByPointDesc();
}