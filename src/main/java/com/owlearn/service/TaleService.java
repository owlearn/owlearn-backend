package com.owlearn.service;

import com.owlearn.dto.TaleCreateRequest;
import com.owlearn.dto.TaleDetailResponse;

public interface TaleService {

    // 동화를 생성하고, 생성된 동화의 ID를 반환
    Long createTale(TaleCreateRequest request);

    // 특정 ID에 해당하는 동화 상세 정보를 반환
    TaleDetailResponse getTale(Long taleId);
}
