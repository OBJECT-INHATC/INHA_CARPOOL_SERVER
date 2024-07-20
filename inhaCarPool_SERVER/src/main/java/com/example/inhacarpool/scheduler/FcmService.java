package com.example.inhacarpool.scheduler;

import static com.example.inhacarpool.common.exception.CustomException.FCM_SEND_ERROR;

import com.example.inhacarpool.common.exception.InhaCarpoolException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class FcmService {

    // 푸시 알림을 보낼 때 필요한 서버 키
    // .env 파일에 저장해서 사용할 예정
    // final String fcmServerKey = Dotenv.load().get("FCM_SERVER_KEY");
    final String fcmServerKey =
            "AAAA-NGypFk:APA91bGFNzWfZi-A_81lR4-TFxXvhCdombcWIMZyfk7GNBKd9NYrOtsL8iQUa5ghtB3UtGFCRIG0ciplrJXw9sgyHQ2gI-gkkggZbCFIDHz4rK9-S_y4_tgTPB9Qdi8OS0DhjOuL2Igb";
    // 푸시 알림을 보낼 때 필요한 FCM 주소
    final String fcmUrl = "https://fcm.googleapis.com/fcm/send";

    public void sendFcmMessage(String title, String body, String carId, Long currentTime) throws InhaCarpoolException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "key=" + fcmServerKey); // 서버 키 설정

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
            ResponseEntity<String> response = restTemplate.postForEntity(fcmUrl, request, String.class);
            log.info("알람 Successfully sent message: " + response.getBody());
        } catch (Exception e) {
            log.info("알람 Error sending message: " + e.getMessage());
            throw new InhaCarpoolException(FCM_SEND_ERROR);
        }
    }

}