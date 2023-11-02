package com.example.inhaCarpool.service;


import com.example.inhaCarpool.dto.UserRequstDTO;
import com.example.inhaCarpool.entity.UserEntity;
import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponseStatus;
import com.example.inhaCarpool.repository.UserInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.inhaCarpool.exception.BaseResponseStatus.DATABASE_INSERT_ERROR;

/**
 * User 관련 기능을 담당하는 Service
 *
 * @author 이상훈
 * @version 1.00    2023.09.01
 */

@Service
@RequiredArgsConstructor // final + not null 생성자 생성 -> 의존성 주입
@Transactional
public class UserService {

    private final UserInterface userInterface;

    // 유저 등록
    public void saveUser(UserRequstDTO userRequstDTO) throws BaseException{
        UserEntity userEntity = UserEntity.builder()
                .uid(userRequstDTO.getUid())
                .nickname(userRequstDTO.getNickname())
                .build();
        try {
            userInterface.save(userEntity);
        } catch (Exception e) {
            throw new BaseException(DATABASE_INSERT_ERROR);
        }

    }


    // 닉네임 업데이트
    public void updateNickname(String uid, String newNickname) throws BaseException {
        UserEntity userEntity = userInterface.findByUid(uid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND)); // 신고가 없는 경우 예외 처리

        // 해당 Uid 컬럼의 nickname 필드를 업데이트
        userEntity.setNickname(newNickname);

    }








}
