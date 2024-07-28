package com.example.inhacarpool.carpool.domain;

import java.time.LocalDateTime;
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
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final boolean status;
    private final Long endTime;

    @Builder
    public Carpool(Long id, String admin, Long nowMember, Long maxMember, GenderType gender,
                   Long startTime, String shortStartPoint, String startPointCoordinate, String detailStartPoint,
                   String shortEndPoint,
                   String endPointCoordinate, String detailEndPoint, LocalDateTime createdAt, LocalDateTime updatedAt,
                   boolean status,
                   Long endTime) {
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.endTime = endTime;
    }


}
