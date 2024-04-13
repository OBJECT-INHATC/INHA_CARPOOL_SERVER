package com.example.inhacarpool.scheduler;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.inhacarpool.exception.BaseException;
import com.example.inhacarpool.history.HistoryService;
import com.example.inhacarpool.history.data.HistoryRequestDTO;
import com.example.inhacarpool.topic.TopicService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarpoolService {

	private final FCMService fcmService;

	private final HistoryService historyService;

	private final TopicService topicService;
	private static final String COLLECTION_NAME = "carpool";

	@SuppressWarnings("unchecked")
	public void scheduleCarpools() {

		log.info("<===스케줄러 실행 중====>");

		Firestore firestore = FirestoreClient.getFirestore(); // Firestore 인스턴스 생성

		// 스케줄러 생성
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2); // 2개의 스케줄러 생성

		// 출발시간으로부터 1시간 이상이 지난 카풀 알람 보내기(topic 구독 해제하므로 1회만 보내짐) -> history 이용내역에 저장
		scheduler.scheduleAtFixedRate(() -> {
			try {

				ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();
				List<QueryDocumentSnapshot> documents = future.get().getDocuments();

				for (QueryDocumentSnapshot document : documents) {
					try {
						Long currentTime = System.currentTimeMillis(); // 현재 시간 (epoch 시간)
						Long startTime = document.getLong("startTime"); // 출발 시간 (epoch 시간)
						String carId = document.getString("carId"); // carId
						String endDetailPoint = document.getString("endDetailPoint"); // 도착지 상세주소
						Boolean status = document.getBoolean("status"); // 알람 전송 여부

						if ((currentTime - startTime) / (60 * 60 * 1000) >= 1) { // 1시간 이상이 지난 카풀에 대하여

							// status가 false인 경우에만 알람 전송 후 이용내역 저장
							if (!Boolean.TRUE.equals(status)) {
								try {

									// 삭제할 카풀의 carId 토픽을 구독한 유저에게 푸시 알림 보내기
									fcmService.sendFcmMessage(endDetailPoint + "까지의 카풀은 어떠셨나요?",
										"이용 내역에서 확인해보세요!", carId, currentTime);

									// 알람전송이 성공한 경우 status를 true로 변경
									try {
										DocumentReference docRef = document.getReference();
										ApiFuture<WriteResult> futureDoc = docRef.update("status", true);

										WriteResult writeResult = futureDoc.get();

										log.info("------------status를 true로 변경완료(알람,이용내역스케줄러) ------------");
									} catch (InterruptedException | ExecutionException e) {
										log.info("------------status를 true로 변경실패(알람,이용내역스케줄러) ------------");
									}

									// firestore의 carpool 데이터를 mysql의 history에 저장하기
									try {
										String member1 = "";
										String member2 = "";
										String member3 = "";
										String member4 = "";

										Long nowMember = document.getLong("nowMember");
										List<String> memberArray = (List<String>)document.get("members");

										if (nowMember == 1) {
											member1 = memberArray.get(0);
										} else if (nowMember == 2) {
											member1 = memberArray.get(0);
											member2 = memberArray.get(1);
										} else if (nowMember == 3) {
											member1 = memberArray.get(0);
											member2 = memberArray.get(1);
											member3 = memberArray.get(2);
										} else if (nowMember == 4) {
											member1 = memberArray.get(0);
											member2 = memberArray.get(1);
											member3 = memberArray.get(2);
											member4 = memberArray.get(3);
										}

										// GeoPoint를 String으로 변환 (firebase -> spring)
										GeoPoint startPoint = document.getGeoPoint("startPoint");
										String startPointString =
											String.valueOf(startPoint.getLatitude()) + "_" + String.valueOf(
												startPoint.getLongitude());
										GeoPoint endPoint = document.getGeoPoint("endPoint");
										String endPointString =
											String.valueOf(endPoint.getLatitude()) + "_" + String.valueOf(
												endPoint.getLongitude());

										// CarpoolResponseDTO 생성
										CarpoolResponseDTO carpool = new CarpoolResponseDTO(
											carId,
											document.getString("admin"),
											member1,
											member2,
											member3,
											member4,
											nowMember,
											document.getLong("maxMember"),
											document.getString("startDetailPoint"),
											startPointString,
											document.getString("startPointName"),
											startTime,
											endDetailPoint,
											endPointString,
											document.getString("endPointName"),
											document.getString("gender")
										);

										HistoryRequestDTO history = historyService.carpoolToHistory(carpool);
										// carpool을 history에 저장
										try {
											historyService.saveHistory(history);
											log.info("------------이용내역에 저장완료(알람,이용내역스케줄러) ------------");
										} catch (IllegalArgumentException e) {
											log.info("------------" + e.getMessage() + " ------------");
										}

									} catch (NullPointerException e) {
										log.info("------------carpool의 필드가 없습니다(알람,이용내역스케줄러) ------------");
										e.printStackTrace();
									}
								} catch (BaseException e) {
									log.info("------------알람 전송에 실패하였습니다(알람,이용내역스케줄러) ------------");
								}
							}
						}
					} catch (NullPointerException e) {
						log.info("------------출발시간,도착지상세주소,carID가 없습니다(알람,이용내역스케줄러) ------------");
						e.printStackTrace();
					}
				}
			} catch (ExecutionException | InterruptedException e) {
				log.info("------------파이어스토어에서 카풀 컬렉션 불러오기 실패(알람스케줄러) ------------");
				e.printStackTrace();
			}

		}, 2, 3600, TimeUnit.SECONDS); // 1시간마다 실행 (60당 1분, 3600당 1시간)

		// 출발시간으로부터 7일 이상 지난 firestore 카풀 삭제하는 스케줄링
		scheduler.scheduleAtFixedRate(() -> {
			try {
				// 54번 줄과 같은 에러
				ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();

				List<QueryDocumentSnapshot> documents = future.get().getDocuments();

				for (QueryDocumentSnapshot document : documents) {
					try {
						Long currentTime = System.currentTimeMillis(); // 현재 시간 (epoch 시간)

						String carId = document.getString("carId"); // carId
						Long startTime = document.getLong("startTime"); // 출발 시간 (epoch 시간)

						if ((currentTime - startTime) / (24 * 60 * 60 * 1000) >= 7) { // 7일 이상이 지난 carpool

							try {
								// firestore에서 carpool을 삭제
								firestore.collection(COLLECTION_NAME).document(carId).delete();
								log.info("삭제된 carpool id: " + carId);
								topicService.deleteTopicByCarId(carId);
							} catch (Exception e) {
								log.info("------------파이어스토어에서 카풀 삭제 실패(카풀삭제스케줄러) ------------");
								e.printStackTrace();
							}

							try {
								// spring에서 carId에 대한 모든 topic 삭제
								topicService.deleteTopicByCarId(carId);
								log.info("------------스프링에서 토픽 삭제 성공(카풀삭제스케줄러) ------------");
							} catch (Exception e) {
								log.info("------------스프링에서 토픽 삭제 실패(카풀삭제스케줄러) ------------");
								e.printStackTrace();
							}
						}
					} catch (NullPointerException e) {
						log.info("------------출발시간, 카풀아이디가 없습니다(카풀삭제스케줄러) ------------");
						e.printStackTrace();
					}
				}
			} catch (ExecutionException | InterruptedException e) {
				log.info("------------파이어스토어에서 카풀 컬렉션 불러오기 실패(카풀삭제스케줄러) ------------");
				e.printStackTrace();
			}
		}, 2, 3600, TimeUnit.SECONDS); // 1시간마다 실행 (60당 1분, 3600당 1시간)
	}
}
