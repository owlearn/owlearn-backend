package com.owlearn.service;

import com.owlearn.dto.TaleCreateRequest;
import com.owlearn.dto.TaleDetailResponse;
import com.owlearn.dto.TaleDto;
import com.owlearn.dto.TaleSummaryResponse;

import java.util.List;

public interface TaleService {

    // 동화를 생성하고, 생성된 동화의 ID를 반환
    Long createTale(TaleCreateRequest request);

    // 특정 ID에 해당하는 동화 상세 정보를 반환
    TaleDetailResponse getTale(Long taleId);

    // 동화를 삽입하고, 삽입한 동화의 ID를 반환
    Long insertTale(TaleDto request);

    // 모든 동화의 상세 정보를 리스트로 반환
    List<TaleSummaryResponse> getAllTales();

    // 특정 ID에 해당하는 동화 정보를 수정하고, 수정된 결과를 반환
    TaleDto updateTale(Long taleId, TaleDto request);

    // 특정 ID에 해당하는 동화를 삭제
    void deleteTale(Long taleId);

}
