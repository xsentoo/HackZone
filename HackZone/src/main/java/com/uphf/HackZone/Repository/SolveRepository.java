package com.uphf.HackZone.Repository;

import com.uphf.HackZone.Entity.SolveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolveRepository extends JpaRepository<SolveEntity,Integer> {
    boolean existsByUserIdAndAttId(int userId,int attId);
}
