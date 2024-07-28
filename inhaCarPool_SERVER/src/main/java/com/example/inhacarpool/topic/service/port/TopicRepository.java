package com.example.inhacarpool.topic.service.port;

import com.example.inhacarpool.user.infrastructure.UserEntity;

public interface TopicRepository {

    Long findHistoryCount(UserEntity userEntity);
}
