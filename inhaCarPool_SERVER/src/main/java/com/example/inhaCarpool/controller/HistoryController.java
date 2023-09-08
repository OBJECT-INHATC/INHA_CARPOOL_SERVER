package com.example.inhaCarpool.controller;


import com.example.inhaCarpool.dto.HistoryRequestDTO;
import com.example.inhaCarpool.service.HistoryService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;


    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = HistoryRequestDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
        })
    @PostMapping("/save")
    public ResponseEntity<HistoryRequestDTO> saveHistory(@RequestBody HistoryRequestDTO historyRequestDTO) {
        try {
            historyService.saveHistory(historyRequestDTO);
            return ResponseEntity.ok(historyRequestDTO); // 컨트롤러에서 응답
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }


}
