package com.yonsei.recommender.document.controller;

import com.yonsei.recommender.document.dto.DocListRequestDto;
import com.yonsei.recommender.document.dto.DocRequestDto;
import com.yonsei.recommender.document.dto.DocSaveRequestDto;
import com.yonsei.recommender.document.service.DocSerivce;
import com.yonsei.recommender.global.common.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
public class DocController {

    private final DocSerivce docService;

    @PostMapping("/cdm/doc")
    public ResponseEntity<ResponseMessage> save(@RequestBody @Valid final DocSaveRequestDto dto) throws Exception {
        docService.save(dto);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(), HttpStatus.OK);
    }

    @PostMapping("/cdm/doc/page")
    public ResponseEntity<ResponseMessage> findById(@RequestBody @Valid DocRequestDto dto) throws Exception {
        ResponseMessage responseMessage = ResponseMessage.builder()
                .data(docService.findById(dto.getId()))
                .build();
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/cdm/doc/list")
    public ResponseEntity<ResponseMessage> findAll(@RequestBody @Valid DocListRequestDto dto) throws Exception {
        ResponseMessage responseMessage = ResponseMessage.builder()
                .data(docService.findAll(dto.getUserId()))
                .build();
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }
}
