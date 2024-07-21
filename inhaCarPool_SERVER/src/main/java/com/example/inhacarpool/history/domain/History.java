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
    private final String shortStartPoint;
    private final String startPointCoordinate;
    private final String detailStartPoint;
    private final Long startTime;
    private final String shortEndPoint;
    private final String endPointCoordinate;
    private final String detailEndPoint;
    private final String gender;

    @Builder
    public History(String carPoolId, String admin, String member1, String member2, String member3, String member4,
                   Long nowMember, Long maxMember, String shortStartPoint, String startPointCoordinate,
                   String detailStartPoint,
                   Long startTime, String shortEndPoint, String endPointCoordinate, String detailEndPoint,
                   String gender) {
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
}
