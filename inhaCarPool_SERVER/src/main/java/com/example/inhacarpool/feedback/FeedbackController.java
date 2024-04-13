package com.example.inhacarpool.feedback;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inhacarpool.feedback.data.dto.FeedbackResponseDTO;
import com.example.inhacarpool.feedback.data.dto.FeedbackSaveDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
@Slf4j
public class FeedbackController {

	private final FeedbackService feedbackService;

	/**
	 * 피드백 저장 apiUrl - /feedback/save
	 * @param feedbackSaveDTO - 피드백 저장 DTO
	 * @return ResponseEntity<?> - 수정 예정
	 */
	@PostMapping("/save")
	public ResponseEntity<?> saveFeedback(
		@Valid
		@RequestBody FeedbackSaveDTO feedbackSaveDTO
	) {
		log.info("[Feedback Table에 피드백 등록 요청]:: {}", feedbackSaveDTO.toString());
		feedbackService.saveFeedback(feedbackSaveDTO);
		log.info("[Feedback Table에 피드백 등록 완료]:: {}", feedbackSaveDTO.toString());

		// ok() 메소드는 ResponseEntity의 정적 메소드로, HTTP 상태 코드 200을 반환한다.
		return ResponseEntity.ok().build();
	}

	/**
	 * 모든 피드백 조회 apiUrl - /feedback/all
	 * @return ResponseEntity<List < FeedbackResponseDTO>> - 모든 피드백 리스트
	 */
	@GetMapping("/all")
	public ResponseEntity<List<FeedbackResponseDTO>> getAllFeedback() {

		log.info("[Feedback Table의 모든 피드백 조회 요청]");
		feedbackService.getAllFeedback();
		log.info("[Feedback Table의 모든 피드백 조회 완료]");

		return ResponseEntity.ok(feedbackService.getAllFeedback());
	}
}
