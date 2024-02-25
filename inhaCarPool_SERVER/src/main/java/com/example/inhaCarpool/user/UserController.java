package com.example.inhaCarpool.user;

import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponse;
import com.example.inhaCarpool.user.data.UserRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public BaseResponse<String>saveUser(@RequestBody UserRequestDTO userRequestDTO) {
        try{
            userService.saveUser(userRequestDTO);
            log.info("==========[[서버에 유저"+ userRequestDTO.getNickname()+" 님을 등록을 완료]]=========> ");
            return new BaseResponse<>("서버에 유저 등록이 완료되었습니다.");
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
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

}