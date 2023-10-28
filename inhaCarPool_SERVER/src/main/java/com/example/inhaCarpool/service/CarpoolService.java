package com.example.inhaCarpool.service;

import com.example.inhaCarpool.dto.CarpoolResponseDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CarpoolService {

    private final FCMService fcmService;

    private static final String COLLECTION_NAME = "carpool";

    public void scheduleCarpools() {

        Firestore firestore = FirestoreClient.getFirestore(); // Firestore 인스턴스 생성

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // 스케줄러 생성

        scheduler.scheduleAtFixedRate(() -> {
            try {
                System.out.println("스케줄러 실행 중");

                ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();

                List<QueryDocumentSnapshot> documents = future.get().getDocuments();

                List<CarpoolResponseDTO> CarpoolResponseDTOS = documents.stream()
                        .map(document -> new CarpoolResponseDTO(
                                document.getString("carId"),
                                document.getLong("startTime"),
                                document.getString("admin").split("_")[1]
                        ))
                        .collect(Collectors.toList());

                Long currentTime = System.currentTimeMillis(); // 현재 시간 (epoch 시간)

                // 현재 시간과 carpoolResponseDTOS의 startTime을 비교
                for (CarpoolResponseDTO carpoolDTO : CarpoolResponseDTOS) {
                    if ((currentTime - carpoolDTO.getStartTime()) / (24 * 60 * 60 * 1000) >= 90) { // 90일 이상이 지난 carpool

//                        System.out.println("현재 시간 : " + currentTime);
//                        System.out.println("carpool의 startTime : " + carpoolDTO.getStartTime());
//                        System.out.println("차이 : " + (currentTime - carpoolDTO.getStartTime()) / (24 * 60 * 60 * 1000) + "일");

                        // spring에 carpool 저장 (추가 예정)
                        // saveHistory(carpoolDTO);

                        // 삭제할 carpool의 carId 토픽을 구독한 유저에게 푸시 알림 보내기
                        fcmService.sendFcmMessage("이전 카풀은 어떠셨나요?", "이용 내역에서 확인해보세요!", carpoolDTO.getCarId(), currentTime);

                        // firestore에서 carpool을 삭제 (현재는 주석처리)
//                        firestore.collection(COLLECTION_NAME).document(carpoolDTO.getCarId()).delete();
                        System.out.println("삭제된 carpool: " + carpoolDTO.getCarId());
                    }
                }

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, 5, 15, TimeUnit.SECONDS); // 5초 후 부터 15초마다 실행
    }
}