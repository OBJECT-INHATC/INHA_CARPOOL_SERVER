package com.example.inhacarpool.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.inhacarpool.exception.BaseException;
import com.example.inhacarpool.exception.BaseResponse;
import com.example.inhacarpool.topic.data.TopicSaveDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName    : TopicController.java 클래스에 대한 설명을 작성합니다.
 *
 */
@Tag(name = "topic API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class TopicController {

	private final TopicService topicService;

	/**
	 * 토픽 저장 - apiURL: /topic/save
	 *
	 * @param topicSaveDto : 토픽 저장 정보
	 * @return ResponseEntity<BaseResponse < String>> : 토픽 저장 성공 여부
	 */
	@PostMapping("/topic/save")
	public ResponseEntity<BaseResponse<String>> saveTopic(
		@RequestBody
		@Valid TopicSaveDto topicSaveDto) throws BaseException {

		long startTime = System.currentTimeMillis();
		topicService.saveTopic(topicSaveDto);
		long timeTaken = System.currentTimeMillis() - startTime;

		log.info("[토픽 저장 완료]:: {}, [실행 시간 ms]:: {}", topicSaveDto, timeTaken);

		return ResponseEntity
			.status(HttpStatusCode.valueOf(200))
			.body(new BaseResponse<>("토픽 저장 완료"));
	}

	// 유저가 카퓰을 나갈 때 토픽 삭제
	@DeleteMapping("/topic/delete")
	public BaseResponse<String> deleteTopicByUidAndCarId(
		@RequestParam(name = "uid") String uid,
		@RequestParam(name = "carId") String carId) {
		try {
			topicService.deleteTopicByUidAndCarId(uid, carId);
			log.info("======" + carId + "=======토픽 삭제되었습니다==========");
			return new BaseResponse<>("토픽이 삭제되었습니다.");
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getBaseExceptionCode());
		}
	}

	// 채팅 알림 on/off 시 서버에서 토픽 리스트 가져와서 구독/해제
	@GetMapping("/user/selectList/{uid}")
	public List<String> getCarIdByUid(@PathVariable String uid) {
		try {
			log.info("======" + uid + "=======토픽 리스트 조회 시작=======");
			return topicService.getCarIdByUid(uid);
		} catch (BaseException exception) {
			return new ArrayList<>();

		}
	}

}
