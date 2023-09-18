package com.example.inhaCarpool.repository;

import com.example.inhaCarpool.entity.ReportEntity;
import com.example.inhaCarpool.entity.TopicEntity;
import com.example.inhaCarpool.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 *    Topic 관련 기능을 담당하는 Repository
 *
 *   @version          1.00    2023.09.01
 *   @author           이상훈
 */

public interface TopicInterface extends JpaRepository<TopicEntity, Long> {

    @Query("select t FROM TopicEntity t WHERE t.users.uid = :uid AND t.carId = :carId")
    Optional<TopicEntity> deleteByUidAndCarId(@Param("uid") String uid, @Param("carId") String carId);


}
