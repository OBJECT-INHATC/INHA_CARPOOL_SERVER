package com.example.inhaCarpool.controller;


import com.example.inhaCarpool.dto.HistoryRequestDTO;
import com.example.inhaCarpool.entity.HistoryEntity;
import com.example.inhaCarpool.service.HistoryService;
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
            @RequestParam(value = "uid") String uid, @RequestParam(value = "nickName") String nickName) {
        List<HistoryRequestDTO> histories = historyService.getHistoryListByMember(uid + "_" + nickName);

        if (histories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(histories);
    }

    @DeleteMapping("/delete/carPoolID")
    public ResponseEntity<HistoryEntity> DeleteCarpool(@RequestParam(value = "carpoolId") String carpoolId){
        boolean result = historyService.deleteHistory(carpoolId);
        if(result){
        return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}



