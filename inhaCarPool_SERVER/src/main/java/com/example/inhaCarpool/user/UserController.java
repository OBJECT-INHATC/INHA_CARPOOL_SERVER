package com.example.inhaCarpool.user;

import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponse;
import com.example.inhaCarpool.user.data.UserSaveDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * User 관련 기능을 담당하는 Controller
 *
 */
@Slf4j
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 유저를 저장합니다
     *
     * @param userSaveDTO - 저장할 유저 정보를 담은 request DTO
     * @return ResponseEntity<Void> - 저장 성공 시 200 OK, 실패 시 400 Bad Request
     */
    @PostMapping("/save")
    public ResponseEntity<Void> saveUser(@RequestBody UserSaveDTO userSaveDTO) {
        try {
            userService.saveUser(userSaveDTO);
            log.info("==========controller: [[서버에 유저"+ userSaveDTO.getNickname()+" 님을 등록을 완료]]=========> ");
            return ResponseEntity.ok().build();
        } catch (Exception e){
            log.info("==========controller: [[서버에 유저"+ userSaveDTO.getNickname()+" 님을 등록을 실패]]=========> ");
            return ResponseEntity.badRequest().build();
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

}
