package com.example.inhacarpool.topic.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.inhacarpool.topic.data.TopicEntity;

/**
 *    Topic 관련 기능을 담당하는 Repository
 *
 *   @version 1.00    2023.09.01
 *   @author 이상훈
 */

public interface TopicInterface extends JpaRepository<TopicEntity, Long> {

	@Query("select t FROM TopicEntity t WHERE t.users.uid = :uid AND t.carId = :carId")
	Optional<TopicEntity> deleteByUidAndCarId(@Param("uid") String uid, @Param("carId") String carId);

	@Query("select t FROM TopicEntity t WHERE t.carId = :carId")
	List<TopicEntity> deleteByCarId(@Param("carId") String carId);

	List<TopicEntity> findByUsersUid(String uid);

	Optional<TopicEntity> findByUsersUidAndCarId(String uid, String carId);

}
