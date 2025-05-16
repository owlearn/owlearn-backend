package com.owlearn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaleRequest {
    private String topic;         // 좋아하는 주제
    private String style;         // 선호하는 그림체
    private int age;              // 연령대
}
