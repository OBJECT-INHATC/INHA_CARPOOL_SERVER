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

    private static final String COLLECTION_NAME = "carpool";

    public void scheduleCarpools() {

        Firestore firestore = FirestoreClient.getFirestore(); // Firestore 인스턴스 생성

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // 스케줄러 생성

        scheduler.scheduleAtFixedRate(() -> {
            try {
                System.out.println("스케줄러 실행 중");

                ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();

                List<QueryDocumentSnapshot> documents = future.get().getDocuments();

                List<CarpoolResponseDTO> updatedCarpoolResponseDTOS = documents.stream()
                        .map(document -> new CarpoolResponseDTO(
                                document.getString("carId"),
                                document.getLong("startTime")
                        ))
                        .collect(Collectors.toList());

                Long currentTime = System.currentTimeMillis(); // 현재 시간 (epoch 시간)

                // 현재 시간과 carpoolResponseDTOS의 startTime을 비교
                for (CarpoolResponseDTO carpoolDTO : updatedCarpoolResponseDTOS) {
                    if (currentTime - carpoolDTO.getStartTime() >= 10 * 24 * 60 * 60 * 1000) { // 10일이 지난 carpool

                        // spring에 carpool 저장 (추가 예정)
                        // saveHistory(carpoolDTO);

                        // carpool을 삭제
                        firestore.collection(COLLECTION_NAME).document(carpoolDTO.getCarId()).delete();
                        System.out.println("삭제된 carpool: " + carpoolDTO.getCarId());
                        System.out.println("삭제된 carpool의 startTime: " + carpoolDTO.getStartTime());


                        // 삭제된 carpool의 carId를 이용해 ... 추가예정
                        // 푸시 알림을 보낸다 ... 추가예정
                    }
                }



            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }


        }, 5, 5, TimeUnit.SECONDS); // 5초 후에 10초마다 스케줄링

    }

}
