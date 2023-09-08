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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historyIdx")
    private Long historyIdx;

    @Column(name = "carPoolId")
    private String carPoolId; // 완료된 카풀 id

    @Column(name = "admin")
    private String admin;

    @Column(name = "member1")
    private String member1;

    @Column(name = "member2")
    private String member2;

    @Column(name = "member3")
    private String member3;

    @Column(name = "nowMember")
    private Long nowMember;

    @Column(name = "maxMemeber")
    private Long maxMember;

    @Column(name = "startDetailPoint")
    private String startDetailPoint;

    @Column(name = "startPoint")
    private String startPoint;

    @Column(name = "startPointName")
    private String startPointName;

    @Column(name = "startTime")
    private Long startTime;

    @Column(name = "endDetailPoint")
    private String endDetailPoint;

    @Column(name = "endPoint")
    private String endPoint;

    @Column(name = "endPointName")
    private String endPointName;

    @Column(name = "gender")
    private String gender;


    @Builder
    public HistoryEntity(String carPoolId, String admin, String member1, String member2, String member3,
                             Long nowMember, Long maxMember, String startDetailPoint, String startPoint,
                             String startPointName, Long startTime, String endDetailPoint, String endPoint,
                             String endPointName, String gender) {
        this.carPoolId = carPoolId;
        this.admin = admin;
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;
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
