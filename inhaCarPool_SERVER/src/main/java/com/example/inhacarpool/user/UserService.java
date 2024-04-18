package com.example.inhacarpool.user;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inhacarpool.history.repo.HistoryInterface;
import com.example.inhacarpool.report.repo.ReportInterface;
import com.example.inhacarpool.user.data.UserEntity;
import com.example.inhacarpool.user.data.dto.UserInfoDto;
import com.example.inhacarpool.user.data.dto.UserSignUpDto;
import com.example.inhacarpool.user.repo.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * The type User service.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

	private final UserRepository userInterface;
	private final ReportInterface reportInterface;
	private final HistoryInterface historyInterface;

	/**
	 * 유저 등록 서비스 로직
	 *
	 * @param userSignUpDto : db에 저장할 유저 정보
	 * @throws DuplicateKeyException : 이미 존재하는 유저일 경우 예외를 controller로 위임
	 * @deprecated 현재는 로그인 시에도 saveUser를 실행하기 때문에 예외로 튕겨버리면 Flutter 앱에서 로그인이 안되는 문제가 있음. 현재는 예외를 피하도록 분기해둠. 변경 예정
	 */
	public void saveUser(UserSignUpDto userSignUpDto) throws DuplicateKeyException {

		// 이미 존재하는 유저가 아닐 때만 저장
		if (!userInterface.existsById(userSignUpDto.getUid())) {
			// DTO를 Entity로 변환
			UserEntity userEntity = UserEntity.builder()
				.uid(userSignUpDto.getUid())
				.nickname(userSignUpDto.getNickname())
				.email(userSignUpDto.getEmail())
				.build();

			userInterface.save(userEntity);
		} /*else {
			throw new DuplicateKeyException("이미 존재하는 유저입니다.");
		}*/
	}

	/**
	 * 유저가 신고 당한 횟수 조회 로직
	 *
	 * @param nickname : 유저 닉네임
	 * @return int : 유저가 신고 당한 횟수
	 * @throws EntityNotFoundException : 해당 nickname을 가진 유저가 존재하지 않을 경우 예외 처리
	 */
	@Transactional(readOnly = true)
	public int countReported(String nickname) throws EntityNotFoundException {

		// findByNickname을 통해 Optional을 반환받지 않은 이유는, 후에 reportInterface.countByReportedUser만을 하기 때문에,
		// UserEntity가 필요없기 때문
		// 해당 엔티티가 필요하다면, findBy 메서드를 사용하고, Optional을 반환받아서 처리하는 게 좋음
		if (userInterface.existsByNickname(nickname)) {
			return reportInterface.countByReportedUser(nickname);
		} else {
			throw new EntityNotFoundException("해당 nickname을 가진 유저가 존재하지 않습니다.");
		}
	}

	/**
	 * 모든 유저 정보 조회 로직 (닉네임, 이메일, 옐로우 카드 수, 레드 카드 여부, 이용기록 수)
	 *
	 * @return List<UserInfoDTO>  : 모든 유저 정보를 담은 DTO 리스트
	 * @deprecated 현재 history와 user 테이블 간의 연관관계가 없어서 FetchJoin이 불가능함 -> 추후 수정 예정
	 */
	@Transactional(readOnly = true)
	public List<UserInfoDto> findAllUserInfo() {
		List<UserEntity> userEntityList = userInterface.findAll();

		return userEntityList.stream().map(userEntity -> UserInfoDto.builder()
			.nickname(userEntity.getNickname())
			.email(userEntity.getEmail())
			.yellowCard(userEntity.getYellowCard())
			.redCard(userEntity.isRedCard())
			.historyCount( // 유저 수 만큼 추가 쿼리 발생 -> FetchJoin으로 해결 예정
				historyInterface.findByMember1ContainingOrMember2ContainingOrMember3ContainingOrMember4Containing(
					userEntity.getUid(), userEntity.getUid(), userEntity.getUid(), userEntity.getUid()
				).size()
			)
			.build()
		).toList();
	}

	/**
	 * 유저의 경고 횟수 0으로 초기화 로직
	 * @param nickname : 경고 횟수를 초기화할 유저의 닉네임
	 * @throws EntityNotFoundException the entity not found exception
	 */
	public void resetYellowCard(String nickname) throws EntityNotFoundException {
		// 해당 엔티티가 필요하기 때문에, existsByNickname을 사용하지 않고, findByNickname을 사용
		Optional<UserEntity> userEntity = userInterface.findByNickname(nickname);
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
		Optional<UserEntity> userEntity = userInterface.findByUid(uid);
		if (userEntity.isPresent()) {
			return userEntity.get().getYellowCard();
		} else {
			throw new EntityNotFoundException("해당 uid를 가진 유저가 존재하지 않습니다.");
		}
	}

}
