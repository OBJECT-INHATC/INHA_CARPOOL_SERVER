package com.example.inhacarpool.topic;

import static com.example.inhacarpool.exception.BaseResponseCode.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.inhacarpool.exception.BaseException;
import com.example.inhacarpool.exception.BaseResponseCode;
import com.example.inhacarpool.topic.data.TopicEntity;
import com.example.inhacarpool.topic.data.TopicRequestDTO;
import com.example.inhacarpool.topic.repo.TopicInterface;
import com.example.inhacarpool.user.data.UserEntity;
import com.example.inhacarpool.user.repo.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName    : TopicService.java 클래스에 대한 설명을 작성합니다.
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor // final + not null 생성자 생성 -> 의존성 주입
public class TopicService {

	private final TopicInterface topicInterface;

	private final UserRepository userInterface;

	// 토픽 저장
	@Transactional
	public void saveTopic(TopicRequestDTO topicRequestDTO) throws BaseException {
		log.info("---------서버에 카풀을 저장하기 시작합니다 ---------");
		UserEntity userEntity = userInterface.findByUid(topicRequestDTO.getUid())
			.orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND)); // 유저가 없는 경우 예외 처리

		// 이미 있는 경우
		if (topicInterface.findByUsersUidAndCarId(topicRequestDTO.getUid(), topicRequestDTO.getCarId()).isPresent()) {
			throw new BaseException(BaseResponseCode.TOPIC_ALREADY_EXIST);
		}

		TopicEntity topicEntity = TopicEntity.builder() // 토픽 저장
			.users(userEntity)
			.carId(topicRequestDTO.getCarId())
			.build();
		try {
			topicInterface.save(topicEntity);
		} catch (Exception e) {
			log.info("---------서버에 카풀 저장 실패 ---------");
			throw new BaseException(DATABASE_INSERT_ERROR);
		}
	}

	// 토픽 삭제
	public void deleteTopicByUidAndCarId(String uid, String carId) throws BaseException {
		// uid와 carId를 사용하여 토픽을 조회
		TopicEntity topicEntity = topicInterface.deleteByUidAndCarId(uid, carId)
			.orElseThrow(() -> new BaseException(BaseResponseCode.TOPIC_NOT_FOUND)); // 토픽이 없는 경우 예외 처리

		// 조회된 토픽을 삭제
		topicInterface.delete(topicEntity);
	}

	// uid로 모든 카풀 아이디 조회
	@Transactional
	public List<String> getCarIdByUid(String uid) throws BaseException {
		List<TopicEntity> topicEntityList = topicInterface.findByUsersUid(uid);
		List<String> carIdList = new ArrayList<>();
		for (TopicEntity topicEntity : topicEntityList) {
			carIdList.add(topicEntity.getCarId());
		}
		return carIdList;
	}

	// carId로 토픽 삭제
	@Transactional
	public void deleteTopicByCarId(String carId) throws BaseException {
		// carId를 사용하여 토픽들을 조회
		List<TopicEntity> topicEntities = topicInterface.deleteByCarId(carId);

		// 조회된 토픽이 없는 경우 예외 처리
		if (topicEntities.isEmpty()) {
			throw new BaseException(BaseResponseCode.TOPIC_NOT_FOUND);
		}

		// 조회된 모든 토픽을 삭제
		topicInterface.deleteAll(topicEntities);
	}

}
