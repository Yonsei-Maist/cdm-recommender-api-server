package com.yonsei.recommender.document.controller;

import com.yonsei.recommender.document.dto.DocRequestDto;
import com.yonsei.recommender.document.dto.DocResponseDto;
import com.yonsei.recommender.document.dto.DocSaveRequestDto;
import com.yonsei.recommender.document.service.DocSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class DocController {

    private final DocSerivce docService;

    @PostMapping("/cdm/doc")
    public void save(@RequestBody DocSaveRequestDto dto) throws Exception {
        docService.save(dto);
    }

    @PostMapping("/cdm/doc/page")
    public DocResponseDto findById(@RequestBody DocRequestDto dto) throws Exception {
        return docService.findById(dto.getId());
    }

}
