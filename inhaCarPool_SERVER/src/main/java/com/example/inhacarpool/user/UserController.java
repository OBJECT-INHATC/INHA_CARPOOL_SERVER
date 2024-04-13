package com.example.inhacarpool.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import com.example.inhacarpool.user.data.UserInfoDTO;
import com.example.inhacarpool.user.data.UserSignUpDTO;

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
	 *                      - uid : 유저 고유번호
	 *                      - nickname : 유저 닉네임
	 *                      - email : 유저 이메일
	 * @return ResponseEntity<String>: 서버에 유저 등록이 완료되었는지 여부
	 * @throws DuplicateKeyException : 이미 존재하는 유저일 경우 예외 처리
	 */
	@PostMapping("/save")
	public ResponseEntity<Map<String, String>> saveUser(
		@Valid // @RequestBody로 받은 객체에 대한 유효성 검사를 진행
		@RequestBody UserSignUpDTO userSignUpDto) throws DuplicateKeyException {

		long startTime = System.currentTimeMillis();

		userService.saveUser(userSignUpDto);
		log.info("[User Table에 유저 등록 완료]:: {}", userSignUpDto);

		long timeTaken = System.currentTimeMillis() - startTime;
		return responseSuccess("유저 등록이 완료되었습니다.", timeTaken);
	}

	/**
	 * 유저가 신고 당한 횟수 조회 - apiURL: /user/count/reported/{nickname}
	 *
	 * @param nickname : 유저 닉네임
	 * @return ResponseEntity<Integer>: 유저가 신고 당한 횟수
	 */
	@GetMapping("/count/reported")
	public ResponseEntity<Integer> getUserReportedCount(
		@RequestParam(value = "nickname") String nickname) {

		long startTime = System.currentTimeMillis();

		int count = userService.getUserReportedCount(nickname);
		log.info("[Report Table에서 유저가 신고당한 횟수 조회 완료]:: {}", count);

		log.info("[{} 실행 완료]:: time taken = {}ms ",
			"getUserReportedCount", System.currentTimeMillis() - startTime);

		return ResponseEntity.ok(count);
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
	 * ExceptionHandler - Exception
	 * - UserController 내에서 발생하는 예외를 처리하는 메소드
	 * @param exception : 발생한 예외
	 * @return ResponseEntity<Map < String, String>> : 예외 처리 결과
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> exceptionHandler(Exception exception) {
		// ResponseEntity를 반환하기 때문에 ResponseEntity가 필요로 하는 header, body, status 채워 넣음
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		log.info("[예외 응답] {}의 ExceptionHandler 호출:: {}", "UserController", exception.getMessage());

		Map<String, String> map = new HashMap<>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code", "400");
		map.put("message", "에러 발생");

		return new ResponseEntity<>(map, responseHeaders, httpStatus);
	}

	/**
	 * ExceptionHandler - DuplicateKeyException
	 * - UserController 내에서 발생하는 중복키 예외를 처리하는 메소드
	 * @param exception : 발생한 예외
	 *          - DuplicateKeyException : 중복키 예외
	 *          - HttpStatus.CONFLICT : 409
	 *          - "해당 유저가 이미 존재합니다." : 예외 메시지
	 *          - 예외 처리 결과를 ResponseEntity 객체로 생성하여 반환
	 * @return ResponseEntity<Map < String, String>> : 예외 처리 결과
	 */
	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<Map<String, String>> exceptionHandler(DuplicateKeyException exception) {
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpStatus = HttpStatus.CONFLICT;

		log.info("[예외 응답] {}의 ExceptionHandler 호출:: exception.message: {}", "UserController", exception.getMessage());

		Map<String, String> map = new HashMap<>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code", "409");
		map.put("message", exception.getMessage());

		return new ResponseEntity<>(map, responseHeaders, httpStatus);
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

	// 응답 성공 시 반환되는 ResponseEntity 객체
	public ResponseEntity<Map<String, String>> responseSuccess(String successMessage, Long timeTaken) {
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpStatus = HttpStatus.OK;

		log.info("[응답 성공] message: {} timeTaken = {}ms", successMessage, timeTaken);

		Map<String, String> map = new HashMap<>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code", "200");
		map.put("message", successMessage);

		return new ResponseEntity<>(map, responseHeaders, httpStatus);
	}

}
