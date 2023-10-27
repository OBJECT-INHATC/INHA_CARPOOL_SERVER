package com.example.inhaCarpool.dto;

import lombok.Data;

@Data
public class CarpoolResponseDTO {
    private String carId;
    private Long startTime;

    public CarpoolResponseDTO(String carId, Long startTime) {
        this.carId = carId;
        this.startTime = startTime;
    }
}
