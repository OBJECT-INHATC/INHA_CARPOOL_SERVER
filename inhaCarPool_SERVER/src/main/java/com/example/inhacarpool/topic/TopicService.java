package com.example.inhacarpool.topic;

import com.example.inhacarpool.common.exception.ExceptionCode;
import com.example.inhacarpool.common.exception.InhaCarpoolException;
import com.example.inhacarpool.topic.data.TopicEntity;
import com.example.inhacarpool.topic.data.TopicSaveDto;
import com.example.inhacarpool.topic.repo.TopicRepository;
import com.example.inhacarpool.user.infrastructure.UserEntity;
import com.example.inhacarpool.user.infrastructure.UserJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Topic service.
 *
 * @ClassName : TopicService.java 클래스에 대한 설명을 작성합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TopicService {

    private final TopicRepository topicRepository;

    private final UserJpaRepository userRepository;

    public void saveTopic(TopicSaveDto topicSaveDto) throws
            InhaCarpoolException { // DuplicateKeyException, EntityNotFoundException을 대체하는 사용자 정의 예외를 던짐

        // 해당 유저가 존재하지 않으면 예외 처리
        Optional<UserEntity> userOptional = userRepository.findById(topicSaveDto.getUid());
        if (userOptional.isEmpty()) {
            throw new InhaCarpoolException(ExceptionCode.USER_NOT_FOUND);
        }
        UserEntity userEntity = userOptional.get();

        // 해당 토픽이 이미 존재하면 예외 처리
        if (topicRepository.existsByUsersUidAndCarId(topicSaveDto.getUid(), topicSaveDto.getCarid())) {
            throw new InhaCarpoolException(ExceptionCode.TOPIC_ALREADY_EXIST);
        }

        // 토픽 생성
        TopicEntity topicEntity = TopicEntity.builder()
                .carId(topicSaveDto.getCarid())
                .users(userEntity)
                .build();

        // 토픽 저장
        topicRepository.save(topicEntity);
    }

    /**
     * Delete topic by uid and car id.
     *
     * @param uid   the uid
     * @param carId the car id
     * @throws InhaCarpoolException the base exception
     */
    // 토픽 삭제
    public void deleteTopicByUidAndCarId(String uid, String carId) throws InhaCarpoolException {
        // uid와 carId를 사용하여 토픽을 조회
        TopicEntity topicEntity = topicRepository.deleteByUidAndCarId(uid, carId)
                .orElseThrow(() -> new InhaCarpoolException(ExceptionCode.TOPIC_NOT_FOUND)); // 토픽이 없는 경우 예외 처리

        // 조회된 토픽을 삭제
        topicRepository.delete(topicEntity);
    }

    /**
     * Gets car id by uid.
     *
     * @param uid the uid
     * @return the car id by uid
     * @throws InhaCarpoolException the base exception
     */
    // uid로 모든 카풀 아이디 조회
    @Transactional
    public List<String> getCarIdByUid(String uid) throws InhaCarpoolException {
        List<TopicEntity> topicEntityList = topicRepository.findByUsersUid(uid);
        List<String> carIdList = new ArrayList<>();
        for (TopicEntity topicEntity : topicEntityList) {
            carIdList.add(topicEntity.getCarId());
        }
        return carIdList;
    }

    /**
     * Delete topic by car id.
     *
     * @param carId the car id
     * @throws InhaCarpoolException the base exception
     */
    // carId로 토픽 삭제
    @Transactional
    public void deleteTopicByCarId(String carId) throws InhaCarpoolException {
        // carId를 사용하여 토픽들을 조회
        List<TopicEntity> topicEntities = topicRepository.deleteByCarId(carId);

        // 조회된 토픽이 없는 경우 예외 처리
        if (topicEntities.isEmpty()) {
            throw new InhaCarpoolException(ExceptionCode.TOPIC_NOT_FOUND);
        }

        // 조회된 모든 토픽을 삭제
        topicRepository.deleteAll(topicEntities);
    }

}
