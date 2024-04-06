package com.example.inhaCarpool.feedback;

import com.example.inhaCarpool.feedback.data.dto.FeedbackSaveDTO;
import com.example.inhaCarpool.feedback.repo.FeedbackRepository;
import com.example.inhaCarpool.user.data.UserEntity;
import com.example.inhaCarpool.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    /**
     * 피드백 저장 서비스 로직
     * @param feedbackSaveDTO 피드백 저장 DTO
     */
    public void saveFeedback(FeedbackSaveDTO feedbackSaveDTO) {
        Optional<UserEntity> userEntity = userRepository.findById(feedbackSaveDTO.getUid());

        if(userEntity.isPresent()) {
            feedbackRepository.save(feedbackSaveDTO.toEntity(userEntity.get()));
        } else {
            log.error("해당 유저가 존재하지 않습니다."); // 수정 예정
        }
    }


//    @Transactional(readOnly = true)
//    public List<FeedbackResponseDTO> getAllFeedback() {
//
//        List<FeedbackEntity> feedbackEntities = feedbackRepository.findAll();
//
//        return feedbackEntities.stream()
//            .map(feedbackEntity -> FeedbackResponseDTO.builder()
//                    .feedbackId(feedbackEntity.getFeedbackId())
//                    .content(feedbackEntity.getContent())
//                    .feedbackType(feedbackEntity.getFeedbackType().toString())
//                    .created_at(feedbackEntity.getCreatedAt())
//                    .user(feedbackEntity.getUser().toResponseDTO())
//    }

}
