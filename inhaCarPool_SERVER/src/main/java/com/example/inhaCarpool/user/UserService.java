package com.example.inhaCarpool.user;


import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.report.repo.ReportInterface;
import com.example.inhaCarpool.user.data.UserEntity;
import com.example.inhaCarpool.user.data.UserSaveDTO;
import com.example.inhaCarpool.user.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User 관련 기능을 담당하는 Service
 *
 * @author 이상훈
 * @version 1.00    2023.09.01
 */

@RequiredArgsConstructor // final + not null 생성자 생성 -> 의존성 주입
@Slf4j
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ReportInterface reportInterface;

    /**
     * 유저를 저장합니다
     *
     * @param userSaveDTO - 저장할 유저 정보를 담은 request DTO
     */
    public void saveUser(UserSaveDTO userSaveDTO) throws Exception {
        log.info("service: 유저 정보 저장 시작===================> ");

        if (userRepository.findByUid(userSaveDTO.getUid()).isPresent()) {
            throw new Exception("이미 존재하는 uid입니다.");
        }

        UserEntity userEntity = UserEntity.builder() // UserEntity 객체 생성 - 비영속 상태
                .uid(userSaveDTO.getUid())
                .nickname(userSaveDTO.getNickname())
                .email(userSaveDTO.getEmail())
                .build();
        userRepository.save(userEntity); // 영속 상태로 변경 + DB에 저장
    }

    // 유저의 경고 횟수 조회
    public int getUserYellowCard(String uid) {
        Optional<UserEntity> userEntity = userRepository.findByUid(uid);
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
