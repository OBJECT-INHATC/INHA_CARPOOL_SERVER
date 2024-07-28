package com.example.inhacarpool.topic.infrastructure;

import com.example.inhacarpool.carpool.infrastructure.CarpoolEntity;
import com.example.inhacarpool.user.infrastructure.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    public TopicEntity(CarpoolEntity carpool, UserEntity user) {
        this.carpool = carpool;
        this.user = user;
    }

}


