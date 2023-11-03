package com.example.inhaCarpool.service;


import com.example.inhaCarpool.dto.TopicRequstDTO;
import com.example.inhaCarpool.entity.TopicEntity;
import com.example.inhaCarpool.entity.UserEntity;
import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponseStatus;
import com.example.inhaCarpool.repository.TopicInterface;
import com.example.inhaCarpool.repository.UserInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

import static com.example.inhaCarpool.exception.BaseResponseStatus.DATABASE_INSERT_ERROR;

/**
 * Topic 관련 기능을 담당하는 Service
 *
 * @author 이상훈
 * @version 1.00    2023.09.01
 */

@Service
@Slf4j
@RequiredArgsConstructor // final + not null 생성자 생성 -> 의존성 주입
@Transactional
public class TopicService {

    private final TopicInterface topicInterface;

    private final UserInterface userInterface;

    // 토픽 등록
    public void saveTopic(TopicRequstDTO topicRequstDTO) throws BaseException {
        log.info("---------서버에 카풀을 저장하기 시작합니다 ---------");
        UserEntity userEntity = userInterface.findByUid(topicRequstDTO.getUid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND)); // 신고가 없는 경우 예외 처리

        TopicEntity topicEntity = TopicEntity.builder()
                .users(userEntity)
                .carId(topicRequstDTO.getCarId())
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
                .orElseThrow(() -> new BaseException(BaseResponseStatus.TOPIC_NOT_FOUND)); // 토픽이 없는 경우 예외 처리

        // 조회된 토픽을 삭제
        topicInterface.delete(topicEntity);
    }

    // uid로 모든 카풀 아이디 조회
    public List<String> getCarIdByUid(String uid) throws BaseException {
        List<TopicEntity> topicEntityList = topicInterface.findByUsersUid(uid);
        List<String> carIdList = new ArrayList<>();
        for (TopicEntity topicEntity : topicEntityList) {
            carIdList.add(topicEntity.getCarId());
        }
        return carIdList;
    }
}
