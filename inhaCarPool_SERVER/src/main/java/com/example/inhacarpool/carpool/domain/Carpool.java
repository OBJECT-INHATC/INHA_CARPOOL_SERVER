package com.example.inhacarpool.carpool.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Carpool {
    private final Long id;
    private final String admin;
    private final String member1;
    private final String member2;
    private final String member3;
    private final String member4;
    private final Long nowMember;
    private final Long maxMember;
    private final GenderType gender;
    private final Long startTime;
    private final String shortStartPoint;
    private final String startPointCoordinate;
    private final String detailStartPoint;
    private final String shortEndPoint;
    private final String endPointCoordinate;
    private final String detailEndPoint;

    @Builder
    public Carpool(Long id, String admin, String member1, String member2, String member3, String member4,
                   Long nowMember, Long maxMember, GenderType gender,
                   Long startTime, String shortStartPoint, String startPointCoordinate, String detailStartPoint,
                   String shortEndPoint,
                   String endPointCoordinate, String detailEndPoint) {
        this.id = id;
        this.admin = admin;
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;
        this.member4 = member4;
        this.nowMember = nowMember;
        this.maxMember = maxMember;
        this.gender = gender;
        this.startTime = startTime;
        this.shortStartPoint = shortStartPoint;
        this.startPointCoordinate = startPointCoordinate;
        this.detailStartPoint = detailStartPoint;
        this.shortEndPoint = shortEndPoint;
        this.endPointCoordinate = endPointCoordinate;
        this.detailEndPoint = detailEndPoint;
    }


}
