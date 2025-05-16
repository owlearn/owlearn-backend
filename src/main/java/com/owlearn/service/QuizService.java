package com.owlearn.service;

import com.owlearn.dto.QuizDto;

import java.util.List;

public interface QuizService {

    // 특정 동화(taleId)에 해당하는 퀴즈 목록을 QuizDto 형태로 반환
    List<QuizDto> getQuizzesByTaleId(Long taleId);
}
