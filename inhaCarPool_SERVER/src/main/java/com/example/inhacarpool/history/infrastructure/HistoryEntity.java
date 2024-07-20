package com.example.inhacarpool.history.infrastructure;

import com.example.inhacarpool.history.domain.History;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "history")
@Getter
@NoArgsConstructor
public class HistoryEntity {

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

    @Column(name = "member4")
    private String member4;

    @Column(name = "nowMember")
    private Long nowMember; // 현재 인원

    @Column(name = "maxMember")
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

    public static HistoryEntity from(History history) {
        return HistoryEntity.builder()
                .carPoolId(history.getCarPoolId())
                .admin(history.getAdmin())
                .member1(history.getMember1())
                .member2(history.getMember2())
                .member3(history.getMember3())
                .member4(history.getMember4())
                .nowMember(history.getNowMember())
                .maxMember(history.getMaxMember())
                .startDetailPoint(history.getStartDetailPoint())
                .startPoint(history.getStartPoint())
                .startPointName(history.getStartPointName())
                .startTime(history.getStartTime())
                .endDetailPoint(history.getEndDetailPoint())
                .endPoint(history.getEndPoint())
                .endPointName(history.getEndPointName())
                .gender(history.getGender())
                .build();
    }

    public History toModel() {
        return History.builder()
                .carPoolId(carPoolId)
                .admin(admin)
                .member1(member1)
                .member2(member2)
                .member3(member3)
                .member4(member4)
                .nowMember(nowMember)
                .maxMember(maxMember)
                .startDetailPoint(startDetailPoint)
                .startPoint(startPoint)
                .startPointName(startPointName)
                .startTime(startTime)
                .endDetailPoint(endDetailPoint)
                .endPoint(endPoint)
                .endPointName(endPointName)
                .gender(gender)
                .build();
    }
}
