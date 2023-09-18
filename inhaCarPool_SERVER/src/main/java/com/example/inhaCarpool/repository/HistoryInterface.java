package com.example.inhaCarpool.repository;


import com.example.inhaCarpool.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryInterface extends JpaRepository<HistoryEntity, Long>{

    HistoryEntity findByCarPoolId(String carPoolId);





}
