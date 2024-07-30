package com.example.inhacarpool.user.controller;

import com.example.inhacarpool.common.response.ApiResponse;
import com.example.inhacarpool.user.controller.port.UserService;
import com.example.inhacarpool.user.controller.request.UserCreateRequest;
import com.example.inhacarpool.user.controller.response.UserResponse;
import com.example.inhacarpool.user.domain.User;
import com.example.inhacarpool.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 API")
@Slf4j
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserService userService;

    @Operation(summary = "유저 회원가입")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<User>> createUser(
            @Valid /*TODO: 테스트 전략 알아보기*/
            @RequestBody UserCreateRequest userCreateRequest) {
        User user = userService.create(userCreateRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(user));
    }

    @Operation(summary = "모든 유저 정보 조회 with 이용 횟수")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserResponse>>> findAllUser() {
        List<UserResponse> users = userService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(users));
    }

    @Operation(summary = "유저의 신고 당한 횟수 조회")
    @GetMapping("/count/reported/{uid}")
    public ResponseEntity<ApiResponse<Integer>> countReported(@PathVariable String uid) {
        int reportedCount = userService.countReported(uid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(reportedCount));
    }

    /**
     * 유저의 경고 횟수를 0으로 초기화 - apiURL: /user/reset/yellow
     *
     * @param nickname : 유저 닉네임
     * @return ResponseEntity response entity
     */
    @PutMapping("/reset/yellow")
    public ResponseEntity<ApiResponse<String>> resetYellowCard(
            @RequestParam(value = "nickname")
            @NotNull String nickname) {

        long startTime = System.currentTimeMillis();
        userServiceImpl.resetYellowCard(nickname);
        long timeTaken = System.currentTimeMillis() - startTime;

        log.info("[유저 경고 횟수 초기화 완료]:: {}, [실행 시간 ms]:: {}", nickname, timeTaken);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(200))
                .body(new ApiResponse<>("유저의 경고 횟수 초기화 완료"));
    }

    /**
     * 유저의 경고 횟수 조회 - apiURL: /user/count/yellow?uid={uid}
     *
     * @param uid : 유저 uid
     * @return ResponseEntity response entity
     */
    @GetMapping("/count/yellow")
    public ResponseEntity<ApiResponse<Integer>> countYellowCard(
            @RequestParam(value = "uid")
            @NotNull @Size(min = 28, max = 28) String uid) {

        long startTime = System.currentTimeMillis();
        int count = userServiceImpl.countYellowCard(uid);
        long timeTaken = System.currentTimeMillis() - startTime;

        log.info("[유저 경고 횟수 조회 완료]:: {}, [실행 시간 ms]:: {}", count, timeTaken);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(200))
                .body(new ApiResponse<>(count));
    }

}
