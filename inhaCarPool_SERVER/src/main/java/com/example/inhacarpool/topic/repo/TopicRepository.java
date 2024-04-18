package com.example.inhacarpool.topic.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.inhacarpool.topic.data.TopicEntity;

/**
 * @ClassName    : TopicRepository.java 클래스에 대한 설명을 작성합니다.
 *
 */
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

	@Query("select t FROM TopicEntity t WHERE t.users.uid = :uid AND t.carId = :carId")
	Optional<TopicEntity> deleteByUidAndCarId(@Param("uid") String uid, @Param("carId") String carId);

	@Query("select t FROM TopicEntity t WHERE t.carId = :carId")
	List<TopicEntity> deleteByCarId(@Param("carId") String carId);

	List<TopicEntity> findByUsersUid(String uid);

	boolean existsByUsersUidAndCarId(String uid, String carId);

}
