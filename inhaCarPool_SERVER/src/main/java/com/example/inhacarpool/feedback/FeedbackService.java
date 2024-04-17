package com.example.inhacarpool.feedback;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inhacarpool.feedback.data.dto.FeedbackResponseDTO;
import com.example.inhacarpool.feedback.data.dto.FeedbackSaveDTO;
import com.example.inhacarpool.feedback.data.entity.FeedbackEntity;
import com.example.inhacarpool.feedback.repo.FeedbackRepository;
import com.example.inhacarpool.user.data.UserEntity;
import com.example.inhacarpool.user.data.dto.UserResponseDTO;
import com.example.inhacarpool.user.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

		if (userEntity.isPresent()) {
			feedbackRepository.save(feedbackSaveDTO.toEntity(userEntity.get()));
		} else {
			log.error("해당 유저가 존재하지 않습니다."); // 수정 예정
		}
	}

	/**
	 * 모든 피드백 조회 서비스 로직
	 *
	 * @return List<FeedbackResponseDTO> 모든 피드백 리스트
	 *
	 * 현재 feedback 테이블을 조회한 후 user 테이블을 조회하기 때문에 feedback 테이블의 row 수 만큼 user 테이블을 조회한다.
	 * 이를 해결하기 위해 fetch join을 사용하거나, + querydsl을 사용하여 한 번에 조회할 수 있다. - 수정 예정
	 */
	@Transactional(readOnly = true)
	public List<FeedbackResponseDTO> getAllFeedback() {

		List<FeedbackEntity> feedbackEntities = feedbackRepository.findAll(); // 모든 피드백 조회. 네임드 쿼리

		return feedbackEntities.stream()
			.map(feedbackEntity -> FeedbackResponseDTO.builder()
				.feedbackId(feedbackEntity.getFeedbackId())
				.content(feedbackEntity.getContent())
				.feedbackType(feedbackEntity.getFeedbackType().toString())
				.created_at(feedbackEntity.getCreatedAt())
				.user(new UserResponseDTO(
						feedbackEntity.getUserEntity().getUid(),
						feedbackEntity.getUserEntity().getNickname(),
						feedbackEntity.getUserEntity().getEmail(),
						feedbackEntity.getUserEntity().getYellowCard(),
						feedbackEntity.getUserEntity().isRedCard()
					)
				)
				.build()
			).toList();
	}

}
