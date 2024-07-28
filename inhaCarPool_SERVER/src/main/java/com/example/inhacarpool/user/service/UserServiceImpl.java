package com.example.inhacarpool.user.service;

import com.example.inhacarpool.common.port.ClockHolder;
import com.example.inhacarpool.history.infrastructure.HistoryJpaRepository;
import com.example.inhacarpool.report.repo.ReportInterface;
import com.example.inhacarpool.user.controller.port.UserService;
import com.example.inhacarpool.user.controller.request.UserCreateRequest;
import com.example.inhacarpool.user.data.dto.UserInfoDto;
import com.example.inhacarpool.user.domain.User;
import com.example.inhacarpool.user.infrastructure.UserEntity;
import com.example.inhacarpool.user.infrastructure.UserJpaRepository;
import com.example.inhacarpool.user.service.port.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;
    private final UserRepository userRepository;
    private final ReportInterface reportInterface;
    private final HistoryJpaRepository historyJpaRepository;
    private final ClockHolder clockHolder;

    public User create(UserCreateRequest userCreateRequest) {
        /*TODO: DuplicateKeyException 처리*/
        User user = User.from(userCreateRequest.to(), clockHolder);
        user = userRepository.save(user);
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Transactional(readOnly = true)
    public User findUser(String uid) {
        return userRepository.findById(uid);
    }

    /**
     * 유저가 신고 당한 횟수 조회 로직
     *
     * @param nickname : 유저 닉네임
     * @return int : 유저가 신고 당한 횟수
     * @throws EntityNotFoundException : 해당 nickname을 가진 유저가 존재하지 않을 경우 예외 처리
     */
//    @Transactional(readOnly = true)
//    public int countReported(String nickname) throws EntityNotFoundException {
//
//        // findByNickname을 통해 Optional을 반환받지 않은 이유는, 후에 reportInterface.countByReportedUser만을 하기 때문에,
//        // UserEntity가 필요없기 때문
//        // 해당 엔티티가 필요하다면, findBy 메서드를 사용하고, Optional을 반환받아서 처리하는 게 좋음
//        if (userJpaRepository.existsByNickname(nickname)) {
//            return reportInterface.countByReportedUser(nickname);
//        } else {
//            throw new EntityNotFoundException("해당 nickname을 가진 유저가 존재하지 않습니다.");
//        }
//    }

    /**
     * 모든 유저 정보 조회 로직 (닉네임, 이메일, 옐로우 카드 수, 레드 카드 여부, 이용기록 수)
     *
     * @return List<UserInfoDTO>  : 모든 유저 정보를 담은 DTO 리스트 현재 history와 user 테이블 간의 연관관계가 없어서 FetchJoin이 불가능함 -> 추후 수정 예정
     */
    @Transactional(readOnly = true)
    public List<UserInfoDto> findAllUserInfo() {
        List<UserEntity> userEntityList = userJpaRepository.findAll();

        return userEntityList.stream().map(userEntity -> UserInfoDto.builder()
                .nickname(userEntity.getNickname())
                .email(userEntity.getEmail())
                .yellowCard(userEntity.getYellowCard())
                .redCard(userEntity.isRedCard())
                .historyCount( // 유저 수 만큼 추가 쿼리 발생 -> FetchJoin으로 해결 예정
                        historyJpaRepository.findByMember1ContainingOrMember2ContainingOrMember3ContainingOrMember4Containing(
                                userEntity.getUid(), userEntity.getUid(), userEntity.getUid(), userEntity.getUid()
                        ).size()
                )
                .build()
        ).toList();
    }

    /**
     * 유저의 경고 횟수 0으로 초기화 로직
     *
     * @param nickname : 경고 횟수를 초기화할 유저의 닉네임
     * @throws EntityNotFoundException the entity not found exception
     */
    public void resetYellowCard(String nickname) throws EntityNotFoundException {
        // 해당 엔티티가 필요하기 때문에, existsByNickname을 사용하지 않고, findByNickname을 사용
        Optional<UserEntity> userEntity = userJpaRepository.findByNickname(nickname);
        if (userEntity.isPresent()) {
            userEntity.get().resetYellowCard();
        } else {
            throw new EntityNotFoundException("해당 nickname을 가진 유저가 존재하지 않습니다.");
        }
    }

    /**
     * 경고 횟수 조회 로직
     *
     * @param uid : 경고 횟수를 조회할 유저의 uid
     * @return int : 경고 횟수
     */
    @Transactional(readOnly = true)
    public int countYellowCard(String uid) throws EntityNotFoundException {
        Optional<UserEntity> userEntity = userJpaRepository.findById(uid);
        if (userEntity.isPresent()) {
            return userEntity.get().getYellowCard();
        } else {
            throw new EntityNotFoundException("해당 uid를 가진 유저가 존재하지 않습니다.");
        }
    }
}
