package com.example.inhaCarpool.service;

import com.example.inhaCarpool.dto.CarpoolResponseDTO;
import com.example.inhaCarpool.dto.HistoryRequestDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
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

    private final HistoryService historyService;

    private static final String COLLECTION_NAME = "carpool";

    public void scheduleCarpools() {

        Firestore firestore = FirestoreClient.getFirestore(); // Firestore 인스턴스 생성

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // 스케줄러 생성

        scheduler.scheduleAtFixedRate(() -> {
            try {
                System.out.println("스케줄러 실행 중");

                ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();

                List<QueryDocumentSnapshot> documents = future.get().getDocuments();

                List<CarpoolResponseDTO> carpoolResponseDTOS = documents.stream()
                        .map(document -> {

                            // member array에 대한 처리
                            String member1 = "";
                            String member2 = "";
                            String member3 = "";

                            Long nowMember = document.getLong("nowMember");
                            List<String> memberArray = (List<String>) document.get("members");

                            if (nowMember == 1) {
                                member1 = memberArray.get(0).toString();
                            } else if(nowMember == 2) {
                                member1 = memberArray.get(0).toString();
                                member2 = memberArray.get(1).toString();
                            } else if(nowMember == 3) {
                                member1 = memberArray.get(0).toString();
                                member2 = memberArray.get(1).toString();
                                member3 = memberArray.get(2).toString();
                            }
                            System.out.println("member1 : " + member1);
                            System.out.println("member2 : " + member2);
                            System.out.println("member3 : " + member3);

                            // GeoPoint를 String으로 변환 (firebase -> spring)
                            GeoPoint startPoint = document.getGeoPoint("startPoint");
                            String startPointString = String.valueOf(startPoint.getLatitude()) + "_" + String.valueOf(startPoint.getLongitude());
                            GeoPoint endPoint = document.getGeoPoint("endPoint");
                            String endPointString = String.valueOf(endPoint.getLatitude()) + "_" + String.valueOf(endPoint.getLongitude());

                            // CarpoolResponseDTO 생성
                            CarpoolResponseDTO carpool = new CarpoolResponseDTO(
                                    document.getString("carId"),
                                    document.getString("admin"),
                                    member1,
                                    member2,
                                    member3,
                                    nowMember,
                                    document.getLong("maxMember"),
                                    document.getString("startDetailPoint"),
                                    startPointString,
                                    document.getString("startPointName"),
                                    document.getLong("startTime"),
                                    document.getString("endDetailPoint"),
                                    endPointString,
                                    document.getString("endPointName"),
                                    document.getString("gender")
                            );
                            return carpool;
                        })
                        .collect(Collectors.toList());

                Long currentTime = System.currentTimeMillis(); // 현재 시간 (epoch 시간)

                // 현재 시간(currentTime)과 carpoolResponseDTOS의 startTime을 비교
                for (CarpoolResponseDTO carpoolDTO : carpoolResponseDTOS) {
                    if ((currentTime - carpoolDTO.getStartTime()) / (24 * 60 * 60 * 1000) >= 90) { // 90일 이상이 지난 carpool

//                        시간 계산 테스트
//                        System.out.println("현재 시간 : " + currentTime);
//                        System.out.println("carpool의 startTime : " + carpoolDTO.getStartTime());
//                        System.out.println("차이 : " + (currentTime - carpoolDTO.getStartTime()) / (24 * 60 * 60 * 1000) + "일");

                        // currentTime에서 hour만 뽑기
                        Date date = new Date(currentTime);
                        SimpleDateFormat sdf = new SimpleDateFormat("HH");
                        String hours = sdf.format(date);

                        // 삭제할 carpool의 carId 토픽을 구독한 유저에게 푸시 알림 보내기
                        fcmService.sendFcmMessage( hours + "시에 이용했던 카풀은 어떠셨나요?", "이용 내역에서 확인해보세요!", carpoolDTO.getCarId(), currentTime);

                        // carpool을 history로 옮기기
                        HistoryRequestDTO history = historyService.carpoolToHistory(carpoolDTO);
                        // spring에 carpool 저장
                        historyService.saveHistory(history);

                        // firestore에서 carpool을 삭제
                        firestore.collection(COLLECTION_NAME).document(carpoolDTO.getCarId()).delete();
                        System.out.println("삭제된 carpool id: " + carpoolDTO.getCarId());

                        // spring에서 carId에 대한 모든 topic 삭제
                        // topicService.deleteTopicByCarId(carpoolDTO.getCarId());
                    }
                }

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, 5, 3600, TimeUnit.SECONDS); // 5초 후 부터 1시간마다 실행
    }
}