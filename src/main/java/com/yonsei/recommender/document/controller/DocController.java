package com.yonsei.recommender.document.controller;

import com.yonsei.recommender.document.service.DocSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DocController {

    private final DocSerivce docService;
}
