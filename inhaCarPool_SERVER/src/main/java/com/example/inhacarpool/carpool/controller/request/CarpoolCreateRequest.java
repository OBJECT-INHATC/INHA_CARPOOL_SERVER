package com.example.inhacarpool.carpool.controller.request;

import com.example.inhacarpool.carpool.domain.CarpoolCreate;
import com.example.inhacarpool.carpool.domain.GenderType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CarpoolCreateRequest {
    private final String id;
    private final String admin;
    private final int maxMember;
    private final GenderType gender;
    private final Long startTime;
    private final String shortStartPoint;
    private final String startPointCoordinate;
    private final String detailStartPoint;
    private final String shortEndPoint;
    private final String endPointCoordinate;
    private final String detailEndPoint;

    public CarpoolCreateRequest(
            @JsonProperty("id") String id,
            @JsonProperty("admin") String admin,
            @JsonProperty("maxMember") int maxMember,
            @JsonProperty("gender") GenderType gender,
            @JsonProperty("startTime") Long startTime,
            @JsonProperty("shortStartPoint") String shortStartPoint,
            @JsonProperty("startPointCoordinate") String startPointCoordinate,
            @JsonProperty("detailStartPoint") String detailStartPoint,
            @JsonProperty("shortEndPoint") String shortEndPoint,
            @JsonProperty("endPointCoordinate") String endPointCoordinate,
            @JsonProperty("detailEndPoint") String detailEndPoint
    ) {
        this.id = id;
        this.admin = admin;
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

    public CarpoolCreate to() {
        return CarpoolCreate.builder()
                .id(id)
                .admin(admin)
                .maxMember(maxMember)
                .gender(gender)
                .startTime(startTime)
                .shortStartPoint(shortStartPoint)
                .startPointCoordinate(startPointCoordinate)
                .detailStartPoint(detailStartPoint)
                .shortEndPoint(shortEndPoint)
                .endPointCoordinate(endPointCoordinate)
                .detailEndPoint(detailEndPoint)
                .build();
    }


}
