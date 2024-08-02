package com.example.inhacarpool.carpool.controller;

import com.example.inhacarpool.carpool.controller.port.CarpoolService;
import com.example.inhacarpool.carpool.controller.request.CarpoolCreateRequest;
import com.example.inhacarpool.carpool.controller.response.CarpoolResponse;
import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "카풀 API")
@Slf4j
@RestController
@RequestMapping("/carpool")
@RequiredArgsConstructor
public class CarpoolController {

    private final CarpoolService carpoolService;

    @Operation(summary = "카풀 생성")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CarpoolResponse>> createCarpool(
            @RequestBody CarpoolCreateRequest carpoolCreateRequest) {
        Carpool carpool = carpoolService.create(carpoolCreateRequest.to());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(CarpoolResponse.from(carpool)));
    }

}
