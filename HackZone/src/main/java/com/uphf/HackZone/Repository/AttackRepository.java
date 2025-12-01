package com.uphf.HackZone.Repository;

import com.uphf.HackZone.Entity.AttackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttackRepository extends JpaRepository<AttackEntity, Integer> {


    Optional<AttackEntity> findByFlag(String flag);
    List<AttackEntity> findByCategory(String category);
}