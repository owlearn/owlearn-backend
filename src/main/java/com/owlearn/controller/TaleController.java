package com.owlearn.controller;

import com.owlearn.dto.*;
import com.owlearn.service.TaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<TaleResponse> createTale(@RequestBody TaleCreateRequest request) {
        Long taleId = taleService.createTale(request);
        return ResponseEntity.ok(new TaleResponse(taleId));
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

    /**
     * 동화 직접 삽입 API
     * @param request 동화의 전체 정보를 담은 요청 DTO
     * @return 생성된 동화의 ID를 포함한 응답 DTO
     */
    @PostMapping("/insert")
    public ResponseEntity<TaleResponse> insertTale(@RequestBody TaleDto request) {
        Long taleId = taleService.insertTale(request);
        return ResponseEntity.ok(new TaleResponse(taleId));
    }

    /**
     * 전체 동화 목록을 조회하는 API
     * @return 모든 동화의 상세 정보를 담은 리스트 응답 DTO
     */
    @GetMapping
    public ResponseEntity<List<TaleSummaryResponse>> getAllTales() {
        return ResponseEntity.ok(taleService.getAllTales());
    }

    /**
     * 특정 동화를 수정하는 API
     * @param taleId 수정할 동화의 고유 ID
     * @param request 수정할 동화 데이터를 담은 요청 DTO
     * @return 수정된 동화의 상세 정보를 담은 응답 DTO
     */
    @PutMapping("/{taleId}")
    public ResponseEntity<TaleDto> updateTale(@PathVariable Long taleId,
                                                         @RequestBody TaleDto request) {
        return ResponseEntity.ok(taleService.updateTale(taleId, request));
    }

    /**
     * 특정 동화를 삭제하는 API
     * @param taleId 삭제할 동화의 고유 ID
     * @return 성공적으로 삭제된 경우, 204 No Content 응답 반환
     */
    @DeleteMapping("/{taleId}")
    public ResponseEntity<Void> deleteTale(@PathVariable Long taleId) {
        taleService.deleteTale(taleId);
        return ResponseEntity.noContent().build();
    }
}
