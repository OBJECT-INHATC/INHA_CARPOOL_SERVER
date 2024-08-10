package com.example.inhacarpool.topic.service.port;

import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.common.port.ClockHolder;
import com.example.inhacarpool.user.domain.User;

public interface TopicRepository {

    Long countHistory(User user);

    void save(User user, Carpool carpool, ClockHolder clockHolder);
}
