package com.yonsei.recommender.document.controller;

import com.yonsei.recommender.document.dto.DocSaveRequestDto;
import com.yonsei.recommender.document.service.DocSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DocController {

    private final DocSerivce docService;

    @PostMapping("/cdm/doc")
    public void save(@RequestBody DocSaveRequestDto dto) throws Exception {
        docService.save(dto);
    }

}
