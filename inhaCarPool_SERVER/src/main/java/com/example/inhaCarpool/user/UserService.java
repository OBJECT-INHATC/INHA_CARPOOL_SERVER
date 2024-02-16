package com.example.inhaCarpool.user;


import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponseStatus;
import com.example.inhaCarpool.report.repo.ReportInterface;
import com.example.inhaCarpool.user.data.UserEntity;
import com.example.inhaCarpool.user.repo.UserInterface;
import com.example.inhaCarpool.user.data.UserRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    private final ReportInterface reportInterface;

    // 유저 등록
    public void saveUser(UserRequestDTO userRequestDTO) throws BaseException{
        log.info("유저 정보 저장 시작===================> "+ userRequestDTO.getNickname());
        UserEntity userEntity = UserEntity.builder()
                .uid(userRequestDTO.getUid())
                .nickname(userRequestDTO.getNickname())
                .email(userRequestDTO.getEmail())
                .build();
        try {
            userInterface.save(userEntity);
        } catch (Exception e) {
            log.info("유저 정보 저장 실패===================> "+ userRequestDTO.getNickname());
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


    // 유저의 경고 횟수 조회
    public int getUserYellowCard(String uid) {
        Optional<UserEntity> userEntity = userInterface.findByUid(uid);
        if (userEntity.isPresent()) {
            return userEntity.get().getYellowCard();
        } else {
            throw new IllegalArgumentException("해당 uid를 가진 유저가 존재하지 않습니다.");
        }
    }


    // 유저의 신고당한 횟수 조회
    public int getUserReportedCount(String nickname) {
        return reportInterface.countByReportedUser(nickname);
    }




}
