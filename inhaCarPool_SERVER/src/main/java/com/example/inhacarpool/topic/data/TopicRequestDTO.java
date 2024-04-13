package com.example.inhacarpool.topic.data;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Topic DTO Class
 *
 * @author 이상훈
 * @version 1.00    2023.09.01
 */

@NoArgsConstructor
@Data
public class TopicRequestDTO {
	private String uid;

	private String carId;

	@Builder
	public TopicRequestDTO(String uid, String carId) {
		this.uid = uid;
		this.carId = carId;
	}

}