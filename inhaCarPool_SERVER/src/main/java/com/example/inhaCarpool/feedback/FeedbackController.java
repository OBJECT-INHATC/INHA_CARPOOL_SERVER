package com.example.inhaCarpool.feedback;

import com.example.inhaCarpool.feedback.data.dto.FeedbackSaveDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
@Slf4j
public class FeedbackController {

    private final FeedbackService feedbackService;

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

//    @GetMapping("/all")
//    public ResponseEntity<> getAllFeedback() {
//
//    }
}
