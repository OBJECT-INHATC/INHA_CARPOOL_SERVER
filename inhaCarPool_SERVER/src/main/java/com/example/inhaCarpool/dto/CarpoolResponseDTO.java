package com.example.inhaCarpool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class CarpoolResponseDTO {
    private String carId;
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

}
