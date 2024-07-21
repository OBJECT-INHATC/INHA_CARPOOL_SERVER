package com.example.inhacarpool.history.controller.response;

import com.example.inhacarpool.history.domain.History;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HistoryResponse {
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

    public static HistoryResponse from(History history) {
        return HistoryResponse.builder()
                .carPoolId(history.getCarPoolId())
                .admin(history.getAdmin())
                .member1(history.getMember1())
                .member2(history.getMember2())
                .member3(history.getMember3())
                .member4(history.getMember4())
                .nowMember(history.getNowMember())
                .maxMember(history.getMaxMember())
                .shortStartPoint(history.getShortStartPoint())
                .startPointCoordinate(history.getStartPointCoordinate())
                .detailStartPoint(history.getDetailStartPoint())
                .startTime(history.getStartTime())
                .shortEndPoint(history.getShortEndPoint())
                .endPointCoordinate(history.getEndPointCoordinate())
                .detailEndPoint(history.getDetailEndPoint())
                .gender(history.getGender())
                .build();
    }
}
