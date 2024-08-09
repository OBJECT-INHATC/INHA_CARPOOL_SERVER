package com.example.inhacarpool.user.controller;

import com.example.inhacarpool.common.response.ApiResponse;
import com.example.inhacarpool.user.controller.port.UserService;
import com.example.inhacarpool.user.controller.request.UserCreateRequest;
import com.example.inhacarpool.user.controller.response.UserResponse;
import com.example.inhacarpool.user.controller.response.UserWithHistoryCount;
import com.example.inhacarpool.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 API")
@Slf4j
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 회원가입")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @Valid /*TODO: 테스트 전략 알아보기*/
            @RequestBody UserCreateRequest userCreateRequest) {
        User user = userService.create(userCreateRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(UserResponse.from(user)));
    }

    @Operation(summary = "모든 유저 정보 조회 with 이용 횟수")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserWithHistoryCount>>> findAllUser() {
        List<UserWithHistoryCount> users = userService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(users));
    }

    @Operation(summary = "유저의 경고 횟수를 0으로 초기화")
    @PutMapping("/reset/yellow/{uid}")
    public ResponseEntity<ApiResponse<UserResponse>> resetYellow(@PathVariable String uid) {
        User user = userService.resetYellow(uid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(UserResponse.from(user)));
    }

    @Operation(summary = "유저의 경고 횟수 조회")
    @GetMapping("/count/yellow/{uid}")
    public ResponseEntity<ApiResponse<Integer>> countYellow(@PathVariable String uid) {
        int yellowCount = userService.countYellow(uid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(yellowCount));
    }

}
