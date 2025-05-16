package com.owlearn.controller;

import com.owlearn.dto.TaleCreateRequest;
import com.owlearn.dto.TaleCreateResponse;
import com.owlearn.dto.TaleDetailResponse;
import com.owlearn.service.TaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tales")
public class TaleController {

    private final TaleService taleService;

    public TaleController(TaleService taleService) {
        this.taleService = taleService;
    }

    /**
     * 새로운 동화를 생성하는 API
     * @param request 동화 생성 요청 DTO
     * @return 생성된 동화의 ID를 담은 응답 DTO
     */
    @PostMapping
    public ResponseEntity<TaleCreateResponse> createTale(@RequestBody TaleCreateRequest request) {
        Long taleId = taleService.createTale(request);
        return ResponseEntity.ok(new TaleCreateResponse(taleId));
    }

    /**
     * 특정 동화 ID에 해당하는 동화 상세 조회 API
     * @param taleId 조회할 동화의 고유 ID
     * @return 동화 상세 정보를 담은 응답 DTO
     */
    @GetMapping("/{taleId}")
    public ResponseEntity<TaleDetailResponse> getTale(@PathVariable Long taleId) {
        return ResponseEntity.ok(taleService.getTale(taleId));
    }
}
