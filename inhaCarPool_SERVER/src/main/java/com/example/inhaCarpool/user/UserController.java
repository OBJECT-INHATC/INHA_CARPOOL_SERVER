package com.example.inhaCarpool.user;

import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponse;
import com.example.inhaCarpool.user.data.UserRequestDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 *    User 관련 기능을 담당하는 Controller
 *
 *   @version          1.0    2023.09.01
 *   @author           이상훈
 */
@Slf4j // Logback 사용을 위한 어노테이션
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 유저가 회원가입 시 서버에 유저 데이터 저장
    @ResponseBody
    @PostMapping("/save")
    public BaseResponse<String>saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        long startTime = System.currentTimeMillis();
        try {
            log.info("[UserController]가 {}를 실행합니다. ", "saveUser");
            userService.saveUser(userRequestDTO);
            log.info("==========[db에 유저 nickname: {}님을 저장 완료]=========> ", userRequestDTO.getNickname());
            return new BaseResponse<>("서버에 유저 등록이 완료되었습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        } finally {
            log.info("[UserController] {}의 Response :: uid = {}, nickname = {}, email = {}, Response Time = {}ms ",
                    "saveUser", userRequestDTO.getUid(), userRequestDTO.getNickname(), userRequestDTO.getEmail(), System.currentTimeMillis() - startTime);
        }
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

    @ExceptionHandler(Exception.class) // UserController 내에서 발생하는 모든 예외를 처리
    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e) {
        // ResponseEntity를 반환하기 때문에 header, body, status 채워 넣음
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.info(e.getMessage());
        log.info("Controller 내 ExceptionHandler 호출");

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

}
