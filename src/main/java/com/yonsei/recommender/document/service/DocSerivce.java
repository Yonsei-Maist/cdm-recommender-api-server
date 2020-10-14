package com.yonsei.recommender.document.service;

import com.yonsei.recommender.document.dao.DocRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DocSerivce {

    @NonNull
    private final DocRepository docRepository;

}
