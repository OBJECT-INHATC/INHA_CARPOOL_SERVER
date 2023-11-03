package com.example.inhaCarpool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarpoolShortDTO {
    private String carId;
    private String endDetailPoint;
    private Long startTime;
}
