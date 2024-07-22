package com.example.inhacarpool.topic;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : TopicController.java 클래스에 대한 설명을 작성합니다.
 */
@Tag(name = "topic API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

//    /**
//     * 토픽 저장 - apiURL: /topic/save
//     *
//     * @param topicSaveDto : 토픽 저장 정보
//     * @return ResponseEntity<BaseResponse < String>> : 토픽 저장 성공 여부
//     */
//    @PostMapping("/topic/save")
//    public ResponseEntity<ApiResponse<String>> saveTopic(
//            @RequestBody
//            TopicSaveDto topicSaveDto) throws InhaCarpoolException {
//
//        long startTime = System.currentTimeMillis();
//        topicService.saveTopic(topicSaveDto);
//        long timeTaken = System.currentTimeMillis() - startTime;
//
//        log.info("[토픽 저장 완료]:: {}, [실행 시간 ms]:: {}", topicSaveDto, timeTaken);
//
//        return ResponseEntity
//                .status(HttpStatusCode.valueOf(200))
//                .body(new ApiResponse<>("토픽 저장 완료"));
//    }

//    // 유저가 카퓰을 나갈 때 토픽 삭제
//    @DeleteMapping("/topic/delete")
//    public ApiResponse<String> deleteTopicByUidAndCarId(
//            @RequestParam(name = "uid") String uid,
//            @RequestParam(name = "carId") String carId) {
//        try {
//            topicService.deleteTopicByUidAndCarId(uid, carId);
//            log.info("======" + carId + "=======토픽 삭제되었습니다==========");
//            return new ApiResponse<>("토픽이 삭제되었습니다.");
//        } catch (InhaCarpoolException exception) {
//            return new ApiResponse<>(exception.getCustomException());
//        }
//    }

//    // 채팅 알림 on/off 시 서버에서 토픽 리스트 가져와서 구독/해제
//    @GetMapping("/user/selectList/{uid}")
//    public List<String> getCarIdByUid(@PathVariable String uid) {
//        try {
//            log.info("======" + uid + "=======토픽 리스트 조회 시작=======");
//            return topicService.getCarIdByUid(uid);
//        } catch (InhaCarpoolException exception) {
//            return new ArrayList<>();
//        }
//    }

}
