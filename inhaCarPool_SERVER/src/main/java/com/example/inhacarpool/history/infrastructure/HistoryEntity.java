package com.example.inhacarpool.history.infrastructure;

import com.example.inhacarpool.history.domain.History;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Deprecated 사용하지 않는 Entity
 */

@Entity
@Table(name = "history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "gender")
    private String gender; // 성별

    @Column(name = "startTime")
    private Long startTime; // 출발 시간

    @Column(name = "shortStartPoint")
    private String shortStartPoint; // 출발지 요약 주소

    @Column(name = "startPointCoordinate")
    private String startPointCoordinate; // 출발지 위도 경도

    @Column(name = "detailStartPoint")
    private String detailStartPoint; // 출발지 상세 주소

    @Column(name = "shortEndPoint")
    private String shortEndPoint; // 도착지 요약 주소

    @Column(name = "endPointCoordinate")
    private String endPointCoordinate; // 도착지 위도 경도

    @Column(name = "detailEndPoint")
    private String detailEndPoint; // 도착지 주소


    @Builder
    public HistoryEntity(String carPoolId, String admin, String member1, String member2, String member3, String member4,
                         Long nowMember, Long maxMember, String shortStartPoint, String startPointCoordinate,
                         String detailStartPoint, Long startTime, String shortEndPoint, String endPointCoordinate,
                         String detailEndPoint, String gender) {
        this.carPoolId = carPoolId;
        this.admin = admin;
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;
        this.member4 = member4;
        this.nowMember = nowMember;
        this.maxMember = maxMember;
        this.shortStartPoint = shortStartPoint;
        this.startPointCoordinate = startPointCoordinate;
        this.detailStartPoint = detailStartPoint;
        this.startTime = startTime;
        this.shortEndPoint = shortEndPoint;
        this.endPointCoordinate = endPointCoordinate;
        this.detailEndPoint = detailEndPoint;
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
                .shortStartPoint(history.getShortStartPoint())
                .startPointCoordinate(history.getStartPointCoordinate())
                .detailStartPoint(history.getDetailStartPoint())
                .startTime(history.getStartTime())
                .shortEndPoint(history.getShortEndPoint())
                .endPointCoordinate(history.getEndPointCoordinate())
                .detailEndPoint(history.getDetailEndPoint())
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
                .shortStartPoint(shortStartPoint)
                .startPointCoordinate(startPointCoordinate)
                .detailStartPoint(detailStartPoint)
                .startTime(startTime)
                .shortEndPoint(shortEndPoint)
                .endPointCoordinate(endPointCoordinate)
                .detailEndPoint(detailEndPoint)
                .gender(gender)
                .build();
    }
}
