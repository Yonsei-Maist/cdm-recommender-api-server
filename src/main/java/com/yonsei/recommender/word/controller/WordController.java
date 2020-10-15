package com.yonsei.recommender.word.controller;

import com.yonsei.recommender.global.common.ResponseMessage;
import com.yonsei.recommender.word.dto.WordRequestDto;
import com.yonsei.recommender.word.dto.WordResponseDto;
import com.yonsei.recommender.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class WordController {

    private final WordService wordService;

    @PostMapping("/cdm/similarity/words")
    public ResponseEntity<ResponseMessage> findByEmrWordId(@RequestBody WordRequestDto dto) throws Exception {
        ResponseMessage responseMessage = ResponseMessage.builder()
                .data(wordService.findByEmrWordId(dto.getWord()))
                .build();

        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }
}
