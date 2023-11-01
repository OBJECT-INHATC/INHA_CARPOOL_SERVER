package com.example.inhaCarpool.controller;

import com.example.inhaCarpool.dto.UserRequstDTO;
import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponse;
import com.example.inhaCarpool.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 *    User 관련 기능을 담당하는 Controller
 *
 *   @version          1.0    2023.09.01
 *   @author           이상훈
 */
@Tag(name = "user API", description = "INHA Carpool Swagger 테스트용")
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 서버에 유저 데이터 저장
    @ResponseBody
    @PostMapping("/save")
    public BaseResponse<String>saveUser(@RequestBody UserRequstDTO userRequstDTO) {
        try{
            this.userService.saveUser(userRequstDTO);
            log.info("=====================================서버에 유저 등록을 완료=====================================> "+ userRequstDTO.getNickname());
            return new BaseResponse<>("서버에 유저 등록이 완료되었습니다.");
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }


    // 유저 닉네임 업데이트
    @PutMapping("/update/{uid}/{newNickname}")
    public BaseResponse<String> updateStatus(@PathVariable String uid, @PathVariable String newNickname) {
        try {
            log.info("=====================================서버에 닉네임 업데이트 완료=====================================> "+ newNickname);
            userService.updateNickname(uid, newNickname);
            return new BaseResponse<>("유저 닉네임 업데이트가 완료되었습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }






}
