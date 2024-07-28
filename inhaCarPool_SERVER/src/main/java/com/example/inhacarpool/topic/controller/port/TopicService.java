package com.example.inhacarpool.topic.controller.port;

import com.example.inhacarpool.user.domain.User;

public interface TopicService {

    Long findHistoryCount(User user);
}
