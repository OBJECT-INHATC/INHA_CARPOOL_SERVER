package com.example.inhacarpool.topic.service.port;

import com.example.inhacarpool.user.domain.User;

public interface TopicRepository {

    Long findHistoryCount(User user);
}
