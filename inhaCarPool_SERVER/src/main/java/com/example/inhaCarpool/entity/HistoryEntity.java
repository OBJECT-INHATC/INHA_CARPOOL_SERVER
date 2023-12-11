package com.example.inhaCarpool.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "history")
@Getter
@NoArgsConstructor
public class HistoryEntity {

    // 스케줄러가 실행되어 완료된 카풀이 저장하는 엔티티

    @Id
    @Column(name = "carPoolId")
    private String carPoolId; // 완료된 카풀 id

    @Column(name = "admin")
    private String admin; // uid_nickname_gender 형식으로 저장

    @Column(name = "member1")
    private String member1; // uid_nickname_gender 형식으로 저장

    @Column(name = "member2")
    private String member2;

    @Column(name = "member3")
    private String member3;

    @Column(name = "memeber4")
    private String member4;

    @Column(name = "nowMember")
    private Long nowMember; // 현재 인원

    @Column(name = "maxMemeber")
    private Long maxMember; // 최대 인원

    @Column(name = "startDetailPoint")
    private String startDetailPoint; // 출발지 요약 주소

    @Column(name = "startPoint")
    private String startPoint; // 출발지 위도 경도

    @Column(name = "startPointName")
    private String startPointName; // 출발지 주소

    @Column(name = "startTime")
    private Long startTime; // 출발 시간

    @Column(name = "endDetailPoint")
    private String endDetailPoint; // 도착지 요약 주소

    @Column(name = "endPoint")
    private String endPoint; // 도착지 위도 경도

    @Column(name = "endPointName")
    private String endPointName; // 도착지 주소

    @Column(name = "gender")
    private String gender; // 성별

    @Builder
    public HistoryEntity(String carPoolId, String admin, String member1, String member2, String member3, String member4,
                             Long nowMember, Long maxMember, String startDetailPoint, String startPoint,
                             String startPointName, Long startTime, String endDetailPoint, String endPoint,
                             String endPointName, String gender) {
        this.carPoolId = carPoolId;
        this.admin = admin;
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;
        this.member4 = member4;
        this.nowMember = nowMember;
        this.maxMember = maxMember;
        this.startDetailPoint = startDetailPoint;
        this.startPoint = startPoint;
        this.startPointName = startPointName;
        this.startTime = startTime;
        this.endDetailPoint = endDetailPoint;
        this.endPoint = endPoint;
        this.endPointName = endPointName;
        this.gender = gender;
    }

}
