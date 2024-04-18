package com.example.inhacarpool.topic.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName    : TopicSaveDto.java 클래스에 대한 설명을 작성합니다.
 * - 토픽 저장 시 필요한 정보를 담은 DTO 클래스
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TopicSaveDto {

	@NotNull(message = "uid는 필수 입력값입니다.")
	@Size(min = 28, max = 28, message = "uid는 28자여야 합니다.")
	private String uid;

	@NotNull(message = "carId는 필수 입력값입니다.")
	@Size(min = 20, max = 20, message = "carId는 20자여야 합니다.")
	private String carid;

}