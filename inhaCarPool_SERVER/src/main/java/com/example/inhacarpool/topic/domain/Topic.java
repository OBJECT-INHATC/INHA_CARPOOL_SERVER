package com.example.inhacarpool.topic.domain;

import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.user.domain.User;
import lombok.Builder;

public class Topic {
    private final Long id;
    private final Carpool carpool;
    private final User user;

    @Builder
    public Topic(Long id, Carpool carpool, User user) {
        this.id = id;
        this.carpool = carpool;
        this.user = user;
    }
}
