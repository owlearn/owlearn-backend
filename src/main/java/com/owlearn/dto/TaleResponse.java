package com.owlearn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaleResponse {
    private String title;                  // 동화 제목
    private List<String> contentPages;     // 각 페이지의 내용 (총 5개)
    private List<String> imageUrls;        // 각 페이지에 대응되는 이미지 URL (총 5개)
}
