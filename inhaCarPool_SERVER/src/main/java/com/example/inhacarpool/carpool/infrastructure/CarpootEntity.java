package com.example.inhacarpool.carpool.infrastructure;

import com.example.inhacarpool.user.infrastructure.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "carpool")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CarpootEntity {

    @Id
    @Column(name = "id")
    private String id; // firebase carpoolId와 동일

    @ManyToOne
    @JoinColumn(name = "admin")
    private UserEntity admin;

    @ManyToOne
    @JoinColumn(name = "member1")
    private UserEntity member1;

    @ManyToOne
    @JoinColumn(name = "member2")
    private UserEntity member2;

    @ManyToOne
    @JoinColumn(name = "member3")
    private UserEntity member3;

    @ManyToOne
    @JoinColumn(name = "member4")
    private UserEntity member4;

    @Column(name = "nowMember")
    private int nowMember;

    @Column(name = "maxMember")
    private int maxMember;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column(name = "startTime")
    private Long startTime; // epoch time

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
    public CarpootEntity(String id, UserEntity admin, UserEntity member1, UserEntity member2, UserEntity member3,
                         UserEntity member4,
                         int nowMember, int maxMember, String shortStartPoint, String startPointCoordinate,
                         String detailStartPoint, Long startTime, String shortEndPoint, String endPointCoordinate,
                         String detailEndPoint) {
        this.id = id;
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
    }
}
