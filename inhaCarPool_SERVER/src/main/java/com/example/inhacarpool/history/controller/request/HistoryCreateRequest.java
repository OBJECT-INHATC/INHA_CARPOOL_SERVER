package com.example.inhacarpool.history.controller.request;

import com.example.inhacarpool.history.domain.History;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HistoryCreateRequest {
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
    public HistoryCreateRequest(
            @JsonProperty("carPoolId") String carPoolId,
            @JsonProperty("admin") String admin,
            @JsonProperty("member1") String member1,
            @JsonProperty("member2") String member2,
            @JsonProperty("member3") String member3,
            @JsonProperty("member4") String member4,
            @JsonProperty("nowMember") Long nowMember,
            @JsonProperty("maxMember") Long maxMember,
            @JsonProperty("startDetailPoint") String shortStartPoint,
            @JsonProperty("startPoint") String startPointCoordinate,
            @JsonProperty("startPointName") String detailStartPoint,
            @JsonProperty("startTime") Long startTime,
            @JsonProperty("endDetailPoint") String shortEndPoint,
            @JsonProperty("endPoint") String endPointCoordinate,
            @JsonProperty("endPointName") String detailEndPoint,
            @JsonProperty("gender") String gender
    ) {
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

    public History to() {

        Long maxMember = Optional.ofNullable(this.maxMember).orElse(4L);
        String member1 = Optional.ofNullable(this.member1).orElse("");
        String member2 = Optional.ofNullable(this.member2).orElse("");
        String member3 = Optional.ofNullable(this.member3).orElse("");
        String member4 = Optional.ofNullable(this.member4).orElse("");

        return History.builder()
                .carPoolId(carPoolId)
                .admin(admin)
                .member1(member1)
                .member2(member2)
                .member3(member3)
                .member4(member4)
                .nowMember(nowMember)
                .maxMember(maxMember)
                .shortStartPoint(shortStartPoint)
                .startPointCoordinate(startPointCoordinate)
                .detailStartPoint(detailStartPoint)
                .startTime(startTime)
                .shortEndPoint(shortEndPoint)
                .endPointCoordinate(endPointCoordinate)
                .detailEndPoint(detailEndPoint)
                .gender(gender)
                .build();
    }

}
