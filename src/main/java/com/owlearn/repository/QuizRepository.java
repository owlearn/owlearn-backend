package com.owlearn.repository;

import com.owlearn.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    // 특정 Tale의 모든 퀴즈 조회
    List<Quiz> findByTaleId(Long taleId);
}