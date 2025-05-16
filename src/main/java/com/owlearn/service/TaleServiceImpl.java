package com.owlearn.service;

import com.owlearn.dto.TaleCreateRequest;
import com.owlearn.dto.TaleDetailResponse;
import com.owlearn.entity.Tale;
import com.owlearn.repository.TaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TaleServiceImpl implements TaleService {

    private final TaleRepository taleRepository;
    private final RestTemplate restTemplate;

    public TaleServiceImpl(TaleRepository taleRepository) {
        this.taleRepository = taleRepository;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Long createTale(TaleCreateRequest request) {
        // FastAPI 호출해서 동화 생성
        /*
        String fastApiUrl = "http://fastapi:8000/generate-tale";
        TaleCreateResponse apiResponse = restTemplate.postForObject(fastApiUrl, request, TaleCreateResponse.class);

        // 동화 DB 저장
        Tale tale = Tale.builder()
                .title(apiResponse.getTitle())
                .contents(apiResponse.getContents())
                .imageUrls(apiResponse.getImageUrls())
                .quizzes(apiResponse.getQuizzes())
                .build();
         */

        // 예시: 응답 저장
        Tale tale = Tale.builder()
                .title("응답제목")
                .contents(List.of("page1", "page2", "page3", "page4", "page5"))
                .imageUrls(List.of("url1", "url2", "url3", "url4", "url5"))
                .quizzes(List.of(/* 퀴즈들 */))
                .build();

        // DB 저장된 내용 반환
        return taleRepository.save(tale).getId();
    }

    @Override
    public TaleDetailResponse getTale(Long taleId) {
        Tale tale = taleRepository.findById(taleId).orElseThrow();
        return new TaleDetailResponse(tale.getTitle(), tale.getContents(), tale.getImageUrls());
    }
}