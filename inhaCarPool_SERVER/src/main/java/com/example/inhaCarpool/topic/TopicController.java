package com.example.inhaCarpool.topic;

import com.example.inhaCarpool.exception.Constants;
import com.example.inhaCarpool.exception.InhaCarpoolException;
import com.example.inhaCarpool.topic.data.TopicRequestDTO;
import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    // 유저의 카풀 참가 시 서버에 토픽 저장
    @ResponseBody
    @PostMapping("/topic/save")
    public BaseResponse<String>saveUser(@RequestBody TopicRequestDTO topicRequestDTO) {
        try{
            topicService.saveTopic(topicRequestDTO);
            log.info("======="+ topicRequestDTO.getCarId()+"====서버에 토픽 등록이 완료되었습니다======> ");
            return new BaseResponse<>("서버에 토픽 등록이 완료되었습니다.");
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // 유저 카풀 나가기 시 토픽 삭제
    @DeleteMapping("/topic/delete")
    public BaseResponse<String> deleteTopicByUidAndCarId(
            @RequestParam(name = "uid") String uid,
            @RequestParam(name = "carId") String carId) {
        try {
            topicService.deleteTopicByUidAndCarId(uid, carId);
            log.info("======"+carId+"=======토픽 삭제되었습니다==========");
            return new BaseResponse<>("토픽이 삭제되었습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // 채팅 알림 on/off 시 서버에서 토픽 리스트 가져와서 구독/해제
    @GetMapping("/user/selectList/{uid}")
    public List<String> getCarIdByUid(@PathVariable String uid) {
        try {
            log.info("======"+uid+"=======토픽 리스트 조회 시작=======");
            return topicService.getCarIdByUid(uid);
        } catch (BaseException exception) {
            return new ArrayList<>();

        }
    }

    @PostMapping(value = "/topic/exception")
    public void exceptionTest() throws InhaCarpoolException {
        throw new InhaCarpoolException(Constants.ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "의도한 에러 발생");
    }
}
