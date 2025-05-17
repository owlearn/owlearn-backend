package com.owlearn.service;

import com.owlearn.dto.TaleApiResponse;
import com.owlearn.dto.TaleCreateRequest;
import com.owlearn.dto.TaleDetailResponse;
import com.owlearn.entity.Quiz;
import com.owlearn.entity.Tale;
import com.owlearn.repository.TaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaleServiceImpl implements TaleService {

    private final TaleRepository taleRepository;
    private final RestTemplate restTemplate;

    public TaleServiceImpl(TaleRepository taleRepository, RestTemplate restTemplate) {
        this.taleRepository = taleRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Long createTale(TaleCreateRequest request) {
        // FastAPI 호출해서 동화 생성
        String fastApiUrl = "http://fastapi:8000/generate-tale";
        TaleApiResponse apiResponse = restTemplate.postForObject(fastApiUrl, request, TaleApiResponse.class);

        if (apiResponse == null) {
            throw new IllegalStateException("FastAPI 응답이 null입니다");
        }

        // DTO를 엔티티로 변환
        List<Quiz> quizzes = Optional.ofNullable(apiResponse.getQuizzes()).orElse(List.of()).stream()
                .map(dto -> Quiz.builder()
                        .questionNumber(dto.getQuestionNumber())
                        .question(dto.getQuestion())
                        .choices(dto.getChoices())
                        .answerIndex(dto.getAnswerIndex())
                        .explanation(dto.getExplanation())
                        .build())
                .collect(Collectors.toList());

        Tale tale = Tale.builder()
                .title(apiResponse.getTitle())
                .contents(apiResponse.getContents())
                .imageUrls(apiResponse.getImageUrls())
                .quizzes(quizzes)
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