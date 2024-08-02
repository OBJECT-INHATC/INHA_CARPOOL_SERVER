package com.example.inhacarpool.topic.controller.port;

import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.user.domain.User;

public interface TopicService {

    Long findHistoryCount(User user);

    void create(User user, Carpool carpool);
}
