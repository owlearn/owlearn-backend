package com.owlearn.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaleResponse {

    private Long id; // 생성된 동화의 DB 기본키 (ID)
}
