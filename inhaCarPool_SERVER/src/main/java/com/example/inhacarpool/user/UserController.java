package com.example.inhacarpool.user;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.inhacarpool.exception.BaseResponse;
import com.example.inhacarpool.user.data.dto.UserInfoDto;
import com.example.inhacarpool.user.data.dto.UserSignUpDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName    : UserController.java 클래스에 대한 설명을 작성합니다.
 *
 */
@Slf4j // Logback 사용을 위한 어노테이션
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	/**
	 * 유저 등록 - apiURL: /user/save
	 *
	 * @param userSignUpDto : 유저 등록 정보
	 * @return ResponseEntity<BaseResponse < String>> : 유저 등록 성공 여부
	 * @throws DuplicateKeyException : 이미 존재하는 유저일 경우 예외를 controller로 위임
	 */
	@PostMapping("/save")
	public ResponseEntity<BaseResponse<String>> saveUser(
		@Valid
		@RequestBody UserSignUpDto userSignUpDto) throws DuplicateKeyException {

		long startTime = System.currentTimeMillis();
		userService.saveUser(userSignUpDto);
		long timeTaken = System.currentTimeMillis() - startTime;

		log.info("[유저 등록 완료]:: {}, [실행 시간 ms]:: {}", userSignUpDto, timeTaken);

		return ResponseEntity
			.status(HttpStatusCode.valueOf(200))
			.body(new BaseResponse<>("유저 등록 성공"));
	}

	/**
	 * 유저가 신고 당한 횟수 조회 - apiURL: /user/count/reported?nickname={nickname}
	 *
	 * @param nickname : 유저 닉네임
	 * @return ResponseEntity<Integer> : 유저가 신고 당한 횟수
	 */
	@GetMapping("/count/reported")
	public ResponseEntity<BaseResponse<Integer>> countReported(
		@RequestParam(value = "nickname") String nickname) {

		long startTime = System.currentTimeMillis();
		int count = userService.countReported(nickname);
		long timeTaken = System.currentTimeMillis() - startTime;

		log.info("[피신고 횟수 조회 완료]:: {}, [실행 시간 ms]:: {}", count, timeTaken);

		return ResponseEntity
			.status(HttpStatusCode.valueOf(200))
			.body(new BaseResponse<>(count));
	}

	/**
	 * 모든 유저 정보 조회 - apiURL: /user/all - 관리자 앱에서 사용 예정
	 * @return ResponseEntity<BaseResponse < List < UserInfoDto>>> : 모든 유저 정보
	 */
	@GetMapping("/all")
	public ResponseEntity<BaseResponse<List<UserInfoDto>>> findAllUserInfo() {

		long startTime = System.currentTimeMillis();
		List<UserInfoDto> userInfoDtoList = userService.findAllUserInfo();
		long timeTaken = System.currentTimeMillis() - startTime;

		log.info("[모든 유저 정보 조회 완료]:: {}, [실행 시간 ms]:: {}", userInfoDtoList, timeTaken);

		return ResponseEntity
			.status(HttpStatusCode.valueOf(200))
			.body(new BaseResponse<>(userInfoDtoList));
	}

	/**
	 * 유저의 경고 횟수를 0으로 초기화 - apiURL: /user/reset/yellow
	 * @param nickname : 유저 닉네임
	 * @return ResponseEntity response entity
	 */
	@PutMapping("/reset/yellow")
	public ResponseEntity<BaseResponse<String>> resetYellowCard(
		@RequestParam(value = "nickname") String nickname) {

		long startTime = System.currentTimeMillis();
		userService.resetYellowCard(nickname);
		long timeTaken = System.currentTimeMillis() - startTime;

		log.info("[유저 경고 횟수 초기화 완료]:: {}, [실행 시간 ms]:: {}", nickname, timeTaken);

		return ResponseEntity
			.status(HttpStatusCode.valueOf(200))
			.body(new BaseResponse<>("유저의 경고 횟수 초기화 완료"));
	}

	/**
	 * 유저의 경고 횟수 조회 - apiURL: /user/count/yellow?uid={uid}
	 * @param uid : 유저 uid
	 * @return ResponseEntity response entity
	 */
	@GetMapping("/count/yellow")
	public ResponseEntity<BaseResponse<Integer>> countYellowCard(
		@RequestParam(value = "uid") String uid) {

		long startTime = System.currentTimeMillis();
		int count = userService.countYellowCard(uid);
		long timeTaken = System.currentTimeMillis() - startTime;

		log.info("[유저 경고 횟수 조회 완료]:: {}, [실행 시간 ms]:: {}", count, timeTaken);

		return ResponseEntity
			.status(HttpStatusCode.valueOf(200))
			.body(new BaseResponse<>(count));
	}

}
