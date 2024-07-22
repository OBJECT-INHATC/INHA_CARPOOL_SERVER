package com.example.inhacarpool.carpool.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Carpool {
    private final Long id;
    private final String admin;
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
    public Carpool(Long id, String admin, Long nowMember, Long maxMember, GenderType gender,
                   Long startTime, String shortStartPoint, String startPointCoordinate, String detailStartPoint,
                   String shortEndPoint,
                   String endPointCoordinate, String detailEndPoint) {
        this.id = id;
        this.admin = admin;
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
