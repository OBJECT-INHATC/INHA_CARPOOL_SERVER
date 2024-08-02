package com.example.inhacarpool.carpool.domain;

import com.example.inhacarpool.common.port.ClockHolder;
import com.example.inhacarpool.user.domain.User;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Carpool {
    private final String id;
    private final User admin;
    private final int nowMember;
    private final int maxMember;
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
    public Carpool(String id, User admin, int nowMember, int maxMember, GenderType gender,
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

    public static Carpool from(CarpoolCreate carpoolCreate, User user, ClockHolder clockHolder) {
        return Carpool.builder()
                .id(carpoolCreate.getId())
                .admin(user)
                .nowMember(1)
                .maxMember(carpoolCreate.getMaxMember())
                .gender(carpoolCreate.getGender())
                .startTime(carpoolCreate.getStartTime())
                .shortStartPoint(carpoolCreate.getShortStartPoint())
                .startPointCoordinate(carpoolCreate.getStartPointCoordinate())
                .detailStartPoint(carpoolCreate.getDetailStartPoint())
                .shortEndPoint(carpoolCreate.getShortEndPoint())
                .endPointCoordinate(carpoolCreate.getEndPointCoordinate())
                .detailEndPoint(carpoolCreate.getDetailEndPoint())
                .createdAt(clockHolder.now())
                .updatedAt(clockHolder.now())
                .status(true)
//                .endTime(carpoolCreate.getStartTime())
                .build();
    }


}
