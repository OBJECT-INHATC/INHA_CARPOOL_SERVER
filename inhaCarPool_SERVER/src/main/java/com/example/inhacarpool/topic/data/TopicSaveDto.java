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

	private String uid;

	private String carid;

}