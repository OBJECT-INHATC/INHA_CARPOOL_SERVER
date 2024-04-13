package com.example.inhacarpool.topic.data;

import com.example.inhacarpool.user.data.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *    FCM_topic DB 엔티티
 *
 *   @version 1.00    2023.09.01
 *   @author 이상훈
 */

@Data
@NoArgsConstructor
@Table(name = "topic")
@Entity
public class TopicEntity {

	// 유저가 새로운 방 참가시 생성(저장)되는 엔티티

	@Id
	@Column(name = "topicIdx")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private Long topicIdx; // 인위적 식별자

	@Column(name = "carId")
	private String carId; // 카풀 Id (파이어베이스의 carId)

	@ManyToOne
	@JoinColumn(name = "users") // 외래키 컬럼
	private UserEntity users; // uid (파이어베이스의 uid)

	@Builder
	public TopicEntity(String carId, UserEntity users) {
		this.carId = carId;
		this.users = users;
	}

}


