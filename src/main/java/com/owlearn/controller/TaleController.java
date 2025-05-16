package com.owlearn.controller;

import com.owlearn.dto.TaleRequest;
import com.owlearn.dto.TaleResponse;
import com.owlearn.service.TaleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tales")
public class TaleController {

    private final TaleService taleService;

    public TaleController(TaleService taleService) {
        this.taleService = taleService;
    }

    @PostMapping
    public ResponseEntity<TaleResponse> createTale(@Valid @RequestBody TaleRequest request) {
        TaleResponse response = taleService.createTale(request);
        return ResponseEntity.ok(response);
    }
}
