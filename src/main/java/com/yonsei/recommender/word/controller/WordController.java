package com.yonsei.recommender.word.controller;

import com.yonsei.recommender.word.dto.WordRequestDto;
import com.yonsei.recommender.word.dto.WordResponseDto;
import com.yonsei.recommender.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class WordController {

    private final WordService wordService;

    @PostMapping("/cdm/similarity/words")
    public WordResponseDto findByEmrWordId(@RequestBody WordRequestDto dto) throws Exception {
        return wordService.findByEmrWordId(dto.getWord());
    }
}
