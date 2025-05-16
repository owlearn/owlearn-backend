package com.owlearn.service;

import com.owlearn.dto.TaleRequest;
import com.owlearn.dto.TaleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaleServiceImpl implements TaleService {

    @Override
    public TaleResponse createTale(TaleRequest request) {
        // TODO: FastAPI 호출해서 동화 생성
        // TODO: 동화내용 바탕 이미지 생성 프롬프트 5개 생성 후 Stable Diffusion API 호출
        // TODO: 결과 받아서 TaleResponse 리턴

        String dummyTitle = "잠자는 부엉이의 모험";
        List<String> dummyPages = List.of(
                "첫 번째 페이지의 내용입니다.",
                "두 번째 페이지의 내용입니다.",
                "세 번째 페이지의 내용입니다.",
                "네 번째 페이지의 내용입니다.",
                "다섯 번째 페이지의 내용입니다."
        );
        List<String> dummyImageUrls = List.of(
                "https://dummyimage.com/600x400/000/fff&text=Page1",
                "https://dummyimage.com/600x400/000/fff&text=Page2",
                "https://dummyimage.com/600x400/000/fff&text=Page3",
                "https://dummyimage.com/600x400/000/fff&text=Page4",
                "https://dummyimage.com/600x400/000/fff&text=Page5"
        );

        return new TaleResponse(dummyTitle, dummyPages, dummyImageUrls);
    }
}
