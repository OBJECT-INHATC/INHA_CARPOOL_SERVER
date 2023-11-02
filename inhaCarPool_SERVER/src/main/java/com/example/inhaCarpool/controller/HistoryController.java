package com.example.inhaCarpool.controller;


import com.example.inhaCarpool.dto.HistoryRequestDTO;
import com.example.inhaCarpool.entity.HistoryEntity;
import com.example.inhaCarpool.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/history")
@RestController
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
            log.info("=====================================이용 내역 저장이 완료되었습니다.=====================================> "+ historyRequestDTO.getStartDetailPoint()+" "+historyRequestDTO.getEndDetailPoint());
            return ResponseEntity.ok(historyRequestDTO); // 컨트롤러에서의 응답
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 이용 내역 조회 메소드
    @GetMapping("/select")
    public ResponseEntity<List<HistoryRequestDTO>> getHistoryListByMember(
            @RequestParam(value = "uid") String uid, @RequestParam(value = "nickName") String nickName, @RequestParam(value = "gender") String gender) {
        List<HistoryRequestDTO> histories = historyService.getHistoryListByMember(uid + "_" + nickName + "_" + gender);

        if (histories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        log.info("=====================================이용 내역 조회가 완료되었습니다.=====================================> "+ nickName);
        return ResponseEntity.ok(histories);
    }

    @DeleteMapping("/delete/carPoolID")
    public ResponseEntity<HistoryEntity> DeleteCarpool(@RequestParam(value = "carpoolId") String carpoolId){
        boolean result = historyService.deleteHistory(carpoolId);
        if(result){
            log.info("=====================================이용 내역 삭제가 완료되었습니다.=====================================> "+ carpoolId);
        return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}



