package com.example.inhaCarpool.user;

import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponse;
import com.example.inhaCarpool.user.data.UserRequestDTO;
import com.example.inhaCarpool.user.data.UserSignUpDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


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
     * 유저 등록
     * @param userSignUpDTO : db에 저장할 유저 정보
     *                       - uid : 유저 고유번호
     *                       - nickname : 유저 닉네임
     *                       - email : 유저 이메일
     * @throws Exception : 이미 존재하는 유저일 경우 예외 처리. service 로부터 전달되어온 예외를 ExceptionHandler로 위임
     * @return BaseResponse<String> : 서버에 유저 등록이 완료되었는지 여부
     */
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(
            @Valid // @RequestBody로 받은 객체에 대한 유효성 검사를 진행
            @RequestBody UserSignUpDTO userSignUpDTO) throws Exception {

        long startTime = System.currentTimeMillis();
        log.info("{}를 실행합니다.", "saveUser");

        userService.saveUser(userSignUpDTO);
        log.info("[User Table 유저 등록 완료]:: uid : {}, nickname : {}, email : {}",
                userSignUpDTO.getUid(), userSignUpDTO.getNickname(), userSignUpDTO.getEmail());

        log.info("[{} 실행 완료]:: time taken = {}ms ",
                "saveUser", System.currentTimeMillis() - startTime);

        return ResponseEntity.ok("유저 등록이 완료되었습니다.");
    }

    // 유저 닉네임 업데이트 (현재 사용 X)
    @PutMapping("/update/{uid}/{newNickname}")
    public BaseResponse<String> updateStatus(@PathVariable String uid, @PathVariable String newNickname) {
            log.info("=======서버에 닉네임 "+newNickname+" 으로 변경 진행=========> ");
        try {
            userService.updateNickname(uid, newNickname);
            log.info("======닉네임 업데이트가 완료되었습니다.=======> "+ newNickname);
            return new BaseResponse<>("유저 닉네임 업데이트가 완료되었습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // 유저 경고 횟수 조회
    @GetMapping("/count/yellow")
    public ResponseEntity<Integer> getUserYellowCard(@RequestParam(value = "uid") String uid) {
        int count = userService.getUserYellowCard(uid);
        log.info("===유저의 경고 횟수 조회 :" + count + "=== ");
        return ResponseEntity.ok(count);
    }

    // 유저 신고당한 횟수 조회
    @GetMapping("/count/reported")
    public ResponseEntity<Integer> getUserReportedCount(@RequestParam(value = "nickname") String nickname) {
        int count = userService.getUserReportedCount(nickname);
        log.info("===유저의 신고당한 횟수 조회 :" + count + "=== ");
        return ResponseEntity.ok(count);
    }


    @PostMapping("/exception")
    public void exceptionTest() throws Exception {
        throw new Exception("test exception");
    }

    /**
     * ExceptionHandler
     * - UserController 내에서 발생하는 예외를 처리하는 메소드
     * @param e : 발생한 예외
     * @return ResponseEntity<Map<String, String>> : 예외 처리 결과
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e) {
        // ResponseEntity를 반환하기 때문에 header, body, status 채워 넣음
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.info("Controller 내 ExceptionHandler 호출:: {} error 발생", e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

}
