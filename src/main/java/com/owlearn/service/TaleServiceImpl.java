package com.owlearn.service;

import com.owlearn.dto.*;
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
        String fastApiUrl = "http://localhost:8000/api/tales";
        TaleDto apiResponse = restTemplate.postForObject(fastApiUrl, request, TaleDto.class);

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

    @Override
    public Long insertTale(TaleDto request) {
        Tale tale = Tale.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .imageUrls(request.getImageUrls())
                .quizzes(request.getQuizzes().stream()
                        .map(dto -> Quiz.builder()
                                .question(dto.getQuestion())
                                .choices(dto.getChoices())
                                .answerIndex(dto.getAnswerIndex())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        Tale saved = taleRepository.save(tale);
        return saved.getId();
    }

    @Override
    public List<TaleSummaryResponse> getAllTales() {
        return taleRepository.findAll().stream()
                .map(tale -> new TaleSummaryResponse(tale.getId(), tale.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public TaleDto updateTale(Long taleId, TaleDto request) {
        Tale tale = taleRepository.findById(taleId)
                .orElseThrow(() -> new RuntimeException("해당 ID의 동화가 존재하지 않습니다."));

        tale.setTitle(request.getTitle());
        tale.setContents(request.getContents());
        tale.setImageUrls(request.getImageUrls());

        // 기존 퀴즈 제거 후 새로 설정
        tale.getQuizzes().clear();
        List<Quiz> newQuizzes = request.getQuizzes().stream()
                .map(dto -> Quiz.builder()
                        .questionNumber(dto.getQuestionNumber())
                        .question(dto.getQuestion())
                        .choices(dto.getChoices())
                        .answerIndex(dto.getAnswerIndex())
                        .explanation(dto.getExplanation())
                        .build())
                .toList();
        tale.getQuizzes().addAll(newQuizzes);

        Tale updated = taleRepository.save(tale);

        return TaleDto.builder()
                .title(updated.getTitle())
                .contents(updated.getContents())
                .imageUrls(updated.getImageUrls())
                .quizzes(updated.getQuizzes().stream()
                        .map(q -> QuizDto.builder()
                                .questionNumber(q.getQuestionNumber())
                                .question(q.getQuestion())
                                .choices(q.getChoices())
                                .answerIndex(q.getAnswerIndex())
                                .explanation(q.getExplanation())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public void deleteTale(Long taleId) {
        Tale tale = taleRepository.findById(taleId)
                .orElseThrow(() -> new RuntimeException("해당 ID의 동화가 존재하지 않습니다."));
        taleRepository.delete(tale);
    }
}