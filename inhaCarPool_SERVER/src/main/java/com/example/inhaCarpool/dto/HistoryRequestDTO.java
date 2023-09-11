package com.example.inhaCarpool.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HistoryRequestDTO {

    private String carPoolId;
    private String admin;
    private String member1;
    private String member2;
    private String member3;
    private Long nowMember;
    private Long maxMember;
    private String startDetailPoint;
    private String startPoint;
    private String startPointName;
    private Long startTime;
    private String endDetailPoint;
    private String endPoint;
    private String endPointName;
    private String gender;

    @Builder
    public HistoryRequestDTO(String carPoolId, String admin, String member1, String member2, String member3, Long nowMember, Long maxMember,
                             String startDetailPoint, String startPoint, String startPointName, Long startTime,
                             String endDetailPoint, String endPoint, String endPointName, String gender) {
        this.carPoolId = carPoolId;
        this.admin = admin;
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;
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
