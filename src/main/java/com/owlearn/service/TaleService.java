package com.owlearn.service;

import com.owlearn.dto.TaleRequest;
import com.owlearn.dto.TaleResponse;

public interface TaleService {
    TaleResponse createTale(TaleRequest request);
}
