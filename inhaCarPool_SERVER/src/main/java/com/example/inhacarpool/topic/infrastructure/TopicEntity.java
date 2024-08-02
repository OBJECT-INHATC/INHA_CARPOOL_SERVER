package com.example.inhacarpool.topic.infrastructure;

import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.carpool.infrastructure.CarpoolEntity;
import com.example.inhacarpool.common.port.ClockHolder;
import com.example.inhacarpool.user.domain.User;
import com.example.inhacarpool.user.infrastructure.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "topic")
@Entity
@Getter
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carpool")
    private CarpoolEntity carpool;

    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;

    private LocalDateTime createdAt;

    public static TopicEntity from(User user, Carpool carpool, ClockHolder clockHolder) {
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.user = UserEntity.from(user);
        topicEntity.carpool = CarpoolEntity.from(carpool);
        topicEntity.createdAt = clockHolder.now();
        return topicEntity;
    }

}
