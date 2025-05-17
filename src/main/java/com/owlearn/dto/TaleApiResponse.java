package com.owlearn.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaleApiResponse {

    private String title;
    private List<String> contents;
    private List<String> imageUrls;
    private List<QuizDto> quizzes;
}

