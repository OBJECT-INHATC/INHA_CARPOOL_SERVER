package com.example.inhacarpool.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.inhacarpool.exception.BaseResponse;
import com.example.inhacarpool.user.data.dto.UserInfoDTO;
import com.example.inhacarpool.user.data.dto.UserSignUpDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *   User 관련 기능을 담당하는 Controller
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
	 * @param userSignUpDto : db에 저장할 유저 정보
	 * @return ResponseEntity<String>: 서버에 유저 등록이 완료되었는지 여부
	 * @throws DuplicateKeyException : 이미 존재하는 유저일 경우 예외 처리
	 */
	@PostMapping("/save")
	public ResponseEntity<BaseResponse<String>> userAdd(
		@Valid // @RequestBody로 받은 객체에 대한 유효성 검사를 진행
		@RequestBody UserSignUpDto userSignUpDto) throws DuplicateKeyException {

		long startTime = System.currentTimeMillis();
		userService.addUser(userSignUpDto);
		long timeTaken = System.currentTimeMillis() - startTime;

		log.info("[User Table에 유저 등록 완료]:: {}, [실행 시간 ms]:: {}", userSignUpDto, timeTaken);

		return ResponseEntity
			.status(HttpStatusCode.valueOf(200))
			.body(new BaseResponse<>("유저 등록 성공"));
	}

	/**
	 * 유저가 신고 당한 횟수 조회 - apiURL: /user/count/reported?nickname={nickname}
	 *
	 * @param nickname : 유저 닉네임
	 * @return ResponseEntity<Integer>: 유저가 신고 당한 횟수
	 */
	@GetMapping("/count/reported")
	public ResponseEntity<BaseResponse<Integer>> getReportedCount(
		@RequestParam(value = "nickname") String nickname) {

		long startTime = System.currentTimeMillis();
		int count = userService.getReportedCount(nickname);
		long timeTaken = System.currentTimeMillis() - startTime;

		log.info("[Report Table에서 유저가 신고당한 횟수 조회 완료]:: {}, [실행 시간 ms]:: {}", count, timeTaken);

		return ResponseEntity
			.status(HttpStatusCode.valueOf(200))
			.body(new BaseResponse<>(count));
	}

	/**
	 * 모든 유저 정보 조회 - apiURL: /user/all
	 *
	 * @return ResponseEntity<Integer>: 유저가 신고한 횟수
	 */
	@GetMapping("/all")
	public ResponseEntity<List<UserInfoDTO>> getAllUserInfo() {
		List<UserInfoDTO> userInfoDtoList = userService.getAllUserInfo();
		log.info("[모든 유저 정보 조회 완료]:: {}", userInfoDtoList.toString());
		return ResponseEntity.ok(userInfoDtoList);
	}

	/**
	 * 유저의 경고 횟수를 0으로 초기화 - apiURL: /user/reset/yellow
	 * @param nickname : 유저 닉네임
	 * @return ResponseEntity
	 */
	@PutMapping("/reset/yellow")
	public ResponseEntity<Map<String, String>> resetYellowCard(@RequestParam(value = "nickname") String nickname) {
		// long startTime = System.currentTimeMillis();

		userService.resetYellowCard(nickname);
		log.info("[유저의 경고 횟수 초기화 완료]:: nickname = {}", nickname);

		// long timeTaken = System.currentTimeMillis() - startTime;
		return ResponseEntity.ok().build();
	}

	// 유저 경고 횟수 조회
	@GetMapping("/count/yellow")
	public ResponseEntity<Integer> getUserYellowCard(@RequestParam(value = "uid") String uid) {
		int count = userService.getUserYellowCard(uid);
		log.info("===유저의 경고 횟수 조회 :" + count + "=== ");
		return ResponseEntity.ok(count);
	}

	/**
	 * ExceptionHandler - MethodArgumentNotValidException
	 * - UserController 내에서 발생하는 유효성 검사 예외를 처리하는 메소드
	 * - @Valid 어노테이션을 통해 유효성 검사를 진행하고, 유효성 검사에 실패할 경우 발생하는 예외를 처리
	 * @param exception : 발생한 예외
	 *          - MethodArgumentNotValidException : 유효성 검사 예외
	 *          - HttpStatus.BAD_REQUEST : 400
	 *          - exception.getMessage() : 예외 메시지
	 *          - 예외 처리 결과를 ResponseEntity 객체로 생성하여 반환
	 * @return ResponseEntity<Map < String, String>> : 예외 처리 결과
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> exceptionHandler(MethodArgumentNotValidException exception) {
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		String validExceptionMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();

		log.info("[예외 응답] {}의 ExceptionHandler 호출:: exception.message: {}", "UserController",
			validExceptionMessage);

		Map<String, String> map = new HashMap<>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code", "400");
		map.put("message", validExceptionMessage);

		return new ResponseEntity<>(map, responseHeaders, httpStatus);
	}

}
