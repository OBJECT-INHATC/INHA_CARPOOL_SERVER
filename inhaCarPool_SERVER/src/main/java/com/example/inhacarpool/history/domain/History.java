package com.example.inhacarpool.history.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class History {
    private final String carPoolId;
    private final String admin;
    private final String member1;
    private final String member2;
    private final String member3;
    private final String member4;
    private final Long nowMember;
    private final Long maxMember;
    private final String startDetailPoint;
    private final String startPoint;
    private final String startPointName;
    private final Long startTime;
    private final String endDetailPoint;
    private final String endPoint;
    private final String endPointName;
    private final String gender;

    @Builder
    public History(String carPoolId, String admin, String member1, String member2, String member3, String member4,
                   Long nowMember, Long maxMember, String startDetailPoint, String startPoint, String startPointName,
                   Long startTime, String endDetailPoint, String endPoint, String endPointName, String gender) {
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
