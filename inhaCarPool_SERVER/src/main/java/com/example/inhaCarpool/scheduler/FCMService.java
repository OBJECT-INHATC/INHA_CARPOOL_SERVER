package com.example.inhaCarpool.scheduler;

import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponseStatus;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.inhaCarpool.exception.BaseResponseStatus.FCM_SEND_ERROR;


@Service
@Slf4j
public class FCMService {

    // 푸시 알림을 보낼 때 필요한 서버 키
    // .env 파일에 저장해서 사용할 예정
    final String _serverKey =
            "AAAA-NGypFk:APA91bGFNzWfZi-A_81lR4-TFxXvhCdombcWIMZyfk7GNBKd9NYrOtsL8iQUa5ghtB3UtGFCRIG0ciplrJXw9sgyHQ2gI-gkkggZbCFIDHz4rK9-S_y4_tgTPB9Qdi8OS0DhjOuL2Igb";

    // 푸시 알림을 보낼 때 필요한 FCM 주소
    final String _fcmUrl = "https://fcm.googleapis.com/fcm/send";

    public void sendFcmMessage(String title, String body, String carId, Long currentTime) throws BaseException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "key=" + _serverKey); // 서버 키 설정

            String notiStatus = "carpoolDone"; // 푸시 알림 type -> 알림 클릭 시 분기 처리를 위해 사용
            String notiTopic = "/topics/" + carId;

            String payload = "{" // 푸시 알림 내용
                    + "\"notification\": {"
                    + "\"title\": \"" + title + "\","
                    + "\"body\": \"" + body + "\","
                    + "\"sound\": \"false\","
                    + "\"priority\": \"high\","
                    + "\"android_channel_id\": \"high_importance_channel\""
                    + "},"
                    + "\"ttl\": \"60s\","
                    + "\"content_available\": true,"
                    + "\"data\": {" 
                    + "\"click_action\": \"FLUTTER_NOTIFICATION_CLICK\","
                    + "\"id\": \"" + notiStatus + "\"," // 푸시 알림 type
                    + "\"status\": \"done\","
                    + "\"action\": \"테스트\","
                    + "\"groupId\": \"" + carId + "\"," // 토픽
                    + "\"sender\": \"test\","
                    + "\"time\": \"" + currentTime + "\""
                    + "},"
                    + "\"to\": \"" + notiTopic + "\""
                    + "}";



            HttpEntity<String> request = new HttpEntity<>(payload, headers); // 푸시 알림 요청 객체
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(_fcmUrl, request, String.class);
            log.info("알람 Successfully sent message: " + response.getBody());
        } catch (Exception e) {
            log.info("알람 Error sending message: " + e.getMessage());
            throw new BaseException(FCM_SEND_ERROR);
        }
    }


}