package com.example.inhaCarpool.dto;

import lombok.Data;

@Data
public class CarpoolResponseDTO {
    private String carId;
    private Long startTime;
    private String admin;

    public CarpoolResponseDTO(String carId, Long startTime, String admin) {
        this.carId = carId;
        this.startTime = startTime;
        this.admin = admin;
    }
}
