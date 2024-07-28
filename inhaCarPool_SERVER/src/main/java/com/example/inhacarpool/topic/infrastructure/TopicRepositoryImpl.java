package com.example.inhacarpool.topic.infrastructure;

import com.example.inhacarpool.topic.service.port.TopicRepository;
import com.example.inhacarpool.user.domain.User;
import com.example.inhacarpool.user.infrastructure.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TopicRepositoryImpl implements TopicRepository {

    private final TopicJpaRepository topicJpaRepository;

    @Override
    public Long findHistoryCount(User user) {
        return topicJpaRepository.countByUser(UserEntity.from(user));
    }
}
