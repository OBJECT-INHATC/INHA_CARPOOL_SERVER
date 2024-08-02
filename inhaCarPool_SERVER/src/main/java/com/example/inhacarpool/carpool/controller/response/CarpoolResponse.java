package com.example.inhacarpool.carpool.controller.response;

import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.carpool.domain.GenderType;
import com.example.inhacarpool.user.controller.response.UserResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarpoolResponse {
    private final String id;
    private final UserResponse admin;
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

    public static CarpoolResponse from(Carpool carpool) {
        return CarpoolResponse.builder()
                .id(carpool.getId())
                .admin(UserResponse.from(carpool.getAdmin()))
                .nowMember(carpool.getNowMember())
                .maxMember(carpool.getMaxMember())
                .gender(carpool.getGender())
                .startTime(carpool.getStartTime())
                .shortStartPoint(carpool.getShortStartPoint())
                .startPointCoordinate(carpool.getStartPointCoordinate())
                .detailStartPoint(carpool.getDetailStartPoint())
                .shortEndPoint(carpool.getShortEndPoint())
                .endPointCoordinate(carpool.getEndPointCoordinate())
                .detailEndPoint(carpool.getDetailEndPoint())
                .build();
    }
}
