package com.owlearn.service;

import com.owlearn.dto.QuizDto;
import com.owlearn.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<QuizDto> getQuizzesByTaleId(Long taleId) {
        return quizRepository.findByTaleId(taleId).stream()
                .map(q -> new QuizDto(
                        q.getQuestionNumber(),
                        q.getQuestion(),
                        q.getChoices(),
                        q.getAnswerIndex(),
                        q.getExplanation()
                ))
                .collect(Collectors.toList());
    }
}