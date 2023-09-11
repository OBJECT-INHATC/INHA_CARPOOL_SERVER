package com.example.inhaCarpool.controller;


import com.example.inhaCarpool.dto.HistoryRequestDTO;
import com.example.inhaCarpool.service.HistoryService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    // 이용 내역 저장 메소드
    @PostMapping("/save")
    public ResponseEntity<HistoryRequestDTO> saveHistory(@RequestBody HistoryRequestDTO historyRequestDTO) {
        try {
            historyService.saveHistory(historyRequestDTO);
            return ResponseEntity.ok(historyRequestDTO); // 컨트롤러에서의 응답
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 이용 내역 조회 메소드
    @GetMapping("/select")
    public ResponseEntity<List<HistoryRequestDTO>> getHistoryListByMember(
            @RequestParam String uid, @RequestParam String nickName) {
        List<HistoryRequestDTO> histories = historyService.getHistoryListByMember(uid + "_" + nickName);

        if (histories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(histories);
    }

}



