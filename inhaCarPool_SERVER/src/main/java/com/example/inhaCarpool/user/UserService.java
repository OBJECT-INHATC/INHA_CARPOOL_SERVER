package com.example.inhaCarpool.user;


import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponseStatus;
import com.example.inhaCarpool.report.repo.ReportInterface;
import com.example.inhaCarpool.user.data.UserEntity;
import com.example.inhaCarpool.user.data.UserSignUpDTO;
import com.example.inhaCarpool.user.repo.UserInterface;
import com.example.inhaCarpool.user.data.UserRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.inhaCarpool.exception.BaseResponseStatus.DATABASE_INSERT_ERROR;

/**
 * User 관련 기능을 담당하는 Service
 *
 * @author 이상훈
 * @version 1.00    2023.09.01
 */

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UserService {

    private final UserInterface userInterface;
    private final ReportInterface reportInterface;

    /**
     * 유저 등록 서비스 로직
     *
     * @param userSignUpDTO : db에 저장할 유저 정보
     * @throws DuplicateKeyException : 이미 존재하는 유저일 경우 예외 처리.
     * throw new Exception 으로 Controller 계층으로 예외를 위임
     *
     */
    public void saveUser(UserSignUpDTO userSignUpDTO) throws DuplicateKeyException {
        // 유효성 검사는 Controller의 Vaild를 통해 이미 완료된 후 Service 계층으로 넘어옴

        if(userInterface.existsById(userSignUpDTO.getUid())) {
            throw new DuplicateKeyException("해당 유저가 이미 존재합니다.");
        }

        // DTO를 Entity로 변환
        UserEntity userEntity = UserEntity.builder()
                .uid(userSignUpDTO.getUid())
                .nickname(userSignUpDTO.getNickname())
                .email(userSignUpDTO.getEmail())
                .build();

        // 영속성 컨텍스트에 저장
        userInterface.save(userEntity);

        // 이 후 Transaction의 commit 시점에 DB에 반영됨
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
