package com.example.inhaCarpool.user;


import com.example.inhaCarpool.history.repo.HistoryInterface;
import com.example.inhaCarpool.report.repo.ReportInterface;
import com.example.inhaCarpool.user.data.UserEntity;
import com.example.inhaCarpool.user.data.UserInfoDTO;
import com.example.inhaCarpool.user.data.UserSignUpDTO;
import com.example.inhaCarpool.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * User 관련 기능을 담당하는 Service
 */

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UserService {

    private final UserRepository userInterface;
    private final ReportInterface reportInterface;
    private final HistoryInterface historyInterface;

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

        /*
          uid가 이미 존재하는지 확인하는 로직
           - 현재는 로그인 시에도 saveUser를 실행하기 때문에 예외로 튕겨버리면 로그인이 안되는 문제가 있어서 주석처리함
             userInterface.existsById(userSignUpDTO.getUid())
         */

        if(userInterface.existsById(userSignUpDTO.getUid())) {
            System.out.println("이미 존재하는 유저입니다."); // 변경 예정
        } else {
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
    }


    // 모든 유저 정보를 가져올건데 하나의 레코드에 닉네임, 학번, 옐로우 카드, 레드카드 수, 이용기록 수를 리턴해주는 api

    /**
     * 모든 유저 정보 조회 로직 (닉네임, 이메일, 옐로우 카드 수, 레드 카드 여부, 이용기록 수)
     *
     * !!! 현재 history와 user 테이블 간의 연관관계가 없어서 FetchJoin이 불가능함
     * !!! 따라서 모든 유저에 대해 history를 조회하는 쿼리를 날려서 이용기록 수를 가져옴 (N+1 문제)
     * !!! 해결하려면 history의 member가 user를 참조하는 uid던지.. 해야할듯
     *
     * @return List<UserInfoDTO> : 모든 유저 정보를 담은 DTO 리스트
     */
    @Transactional(readOnly = true)
    public List<UserInfoDTO> getAllUserInfo() {
        List<UserEntity> userEntityList = userInterface.findAll();

        return userEntityList.stream().map(userEntity -> UserInfoDTO.builder()
            .nickname(userEntity.getNickname())
            .email(userEntity.getEmail())
            .yellowCard(userEntity.getYellowCard())
            .redCard(userEntity.isRedCard())
            .historyCount(
                    historyInterface.findByMember1ContainingOrMember2ContainingOrMember3ContainingOrMember4Containing(
                            userEntity.getUid(), userEntity.getUid(), userEntity.getUid(), userEntity.getUid()
                    ).size()
            )
            .build()).toList();
    }

    /**
     * 유저의 경고 횟수 0으로 초기화 로직
     * @param nickname : 경고 횟수를 초기화할 유저의 닉네임
     * @return
     */
    public void resetYellowCard(String nickname) {
        Optional<UserEntity> userEntity = userInterface.findByNickname(nickname);
        if (userEntity.isPresent()) {
            userEntity.get().resetYellowCard();
        } else {
            throw new IllegalArgumentException("해당 nickname을 가진 유저가 존재하지 않습니다.");
        }
    }


    // 유저의 신고당한 횟수 조회
    @Transactional(readOnly = true)
    public int getUserReportedCount(String nickname) {
        return reportInterface.countByReportedUser(nickname);
    }



    // 유저의 경고 횟수 조회
    @Transactional(readOnly = true)
    public int getUserYellowCard(String uid) {
        Optional<UserEntity> userEntity = userInterface.findByUid(uid);
        if (userEntity.isPresent()) {
            return userEntity.get().getYellowCard();
        } else {
            throw new IllegalArgumentException("해당 uid를 가진 유저가 존재하지 않습니다.");
        }
    }







}
