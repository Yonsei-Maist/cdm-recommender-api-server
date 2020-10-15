package com.yonsei.recommender.word.controller;

import com.yonsei.recommender.global.common.ResponseMessage;
import com.yonsei.recommender.word.dto.PagingDto;
import com.yonsei.recommender.word.dto.SimilarityWordRequestDto;
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
    public ResponseEntity<ResponseMessage> findByEmrWordId(@RequestBody SimilarityWordRequestDto dto) throws Exception {
        ResponseMessage responseMessage = ResponseMessage.builder()
                .data(wordService.findByEmrWordId(dto.getWord()))
                .build();
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/cdm/words/list")
    public ResponseEntity<ResponseMessage> findAll(@RequestBody PagingDto pagingDto) throws Exception {
        int totalRecordCount = wordService.countAll();
        pagingDto.setTotalRecordCount(totalRecordCount);

        if(totalRecordCount!=0){
            ResponseMessage responseMessage = ResponseMessage.builder()
                    .data(wordService.findAll(pagingDto))
                    .build();
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("/cdm/words")
    public ResponseEntity<ResponseMessage> delete() throws Exception {
        wordService.delete();
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(), HttpStatus.OK);
    }

}
