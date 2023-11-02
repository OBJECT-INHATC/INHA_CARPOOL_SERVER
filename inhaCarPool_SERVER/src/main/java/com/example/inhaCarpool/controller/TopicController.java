package com.example.inhaCarpool.controller;

import com.example.inhaCarpool.dto.TopicRequstDTO;
import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponse;
import com.example.inhaCarpool.service.TopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 *    Topic 관련 기능을 담당하는 Controller
 *
 *   @version          1.0    2023.09.01
 *   @author           이상훈
 */
@Tag(name = "topic API", description = "INHA Carpool Swagger 테스트용")
@Slf4j
@RestController
public class TopicController {
    private final TopicService topicService;
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }


    // 서버에 토픽 저장
    @ResponseBody
    @PostMapping("/topic/save")
    public BaseResponse<String>saveUser(@RequestBody TopicRequstDTO topicRequstDTO) {
        try{
            this.topicService.saveTopic(topicRequstDTO);
            log.info("=====================================서버에 토픽 등록이 완료되었습니====================================> "+ topicRequstDTO.getCarId());
            return new BaseResponse<>("서버에 토픽 등록이 완료되었습니다.");
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // 나의 Uid, 카풀 ID로 토픽 삭제
    @DeleteMapping("/topic/delete")
    public BaseResponse<String> deleteTopicByUidAndCarId(
            @RequestParam(name = "uid") String uid,
            @RequestParam(name = "carId") String carId) {
        try {
            topicService.deleteTopicByUidAndCarId(uid, carId);
            log.info("=====================================토픽이 삭제되었습니다.====================================> "+ carId);
            return new BaseResponse<>("토픽이 삭제되었습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/user/selectList/{uid}")
    public List<String> getCarIdByUid(@PathVariable String uid) {
        try {
            log.info("=====================================토픽 리스트 조회 시작====================================> "+ uid);
            return topicService.getCarIdByUid(uid);
        } catch (BaseException exception) {
            return new ArrayList<>();

        }
    }


}
