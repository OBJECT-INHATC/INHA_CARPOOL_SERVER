package com.example.inhaCarpool.service;


import com.example.inhaCarpool.dto.UserRequstDTO;
import com.example.inhaCarpool.entity.UserEntity;
import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponseStatus;
import com.example.inhaCarpool.repository.UserInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.inhaCarpool.exception.BaseResponseStatus.DATABASE_INSERT_ERROR;

/**
 * User 관련 기능을 담당하는 Service
 *
 * @author 이상훈
 * @version 1.00    2023.09.01
 */

@RequiredArgsConstructor // final + not null 생성자 생성 -> 의존성 주입
@Slf4j
@Service
public class UserService {

    private final UserInterface userInterface;

    // 유저 등록
    public void saveUser(UserRequstDTO userRequstDTO) throws BaseException{
        log.info("유저 정보 저장 시작===================> "+ userRequstDTO.getNickname());
        UserEntity userEntity = UserEntity.builder()
                .uid(userRequstDTO.getUid())
                .nickname(userRequstDTO.getNickname())
                .email(userRequstDTO.getEmail())
                .build();
        try {
            userInterface.save(userEntity);
        } catch (Exception e) {
            log.info("유저 정보 저장 실패===================> "+ userRequstDTO.getNickname());
            throw new BaseException(DATABASE_INSERT_ERROR);
        }

    }


    // 닉네임 업데이트
    @Transactional
    public void updateNickname(String uid, String newNickname) throws BaseException {
        UserEntity userEntity = userInterface.findByUid(uid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND)); // 신고가 없는 경우 예외 처리
        // 해당 Uid 컬럼의 nickname 필드를 업데이트
        userEntity.setNickname(newNickname);

    }








}
