package com.example.inhaCarpool.feedback.repo;

import com.example.inhaCarpool.feedback.data.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FeedbackRepository
 * JPA를 이용한 CRUD
 * JpaRepository를 상속받아 네임드 쿼리를 사용
 * CustomRepository를 상속받아 QueryDSL을 사용

 */
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
}
