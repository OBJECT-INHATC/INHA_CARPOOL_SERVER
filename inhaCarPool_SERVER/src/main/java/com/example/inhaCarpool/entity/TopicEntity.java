package com.example.inhaCarpool.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *    FCM_topic DB 엔티티
 *
 *   @version          1.00    2023.09.01
 *   @author           이상훈
 */

@Data
@NoArgsConstructor
@Table(name = "topic")
@Entity
public class TopicEntity {

    @Id
    @Column(name = "topicIdx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicIdx;

    @Column(name = "carId")
    private String carId; // 카풀 Id

    @ManyToOne
    @JoinColumn(name = "users")
    private UserEntity users;


    @Builder
    public TopicEntity(String carId, UserEntity users) {
        this.carId = carId;
        this.users = users;

    }



}


