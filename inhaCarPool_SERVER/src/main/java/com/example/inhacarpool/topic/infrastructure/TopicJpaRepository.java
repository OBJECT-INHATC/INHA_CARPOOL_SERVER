package com.example.inhacarpool.topic.infrastructure;

import com.example.inhacarpool.user.infrastructure.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicJpaRepository extends JpaRepository<TopicEntity, Long> {

    Long countByUser(UserEntity userEntity);

}
