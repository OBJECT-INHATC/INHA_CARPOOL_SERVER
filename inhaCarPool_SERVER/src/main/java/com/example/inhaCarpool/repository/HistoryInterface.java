package com.example.inhaCarpool.repository;


import com.example.inhaCarpool.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryInterface extends JpaRepository<HistoryEntity, Long>{

    HistoryEntity findByCarPoolId(String carPoolId);

    List<HistoryEntity> findByMember1ContainingOrMember2ContainingOrMember3Containing(String member1, String member2, String member3);




}
