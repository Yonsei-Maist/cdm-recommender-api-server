package com.yonsei.recommender.document.service;

import com.yonsei.recommender.document.dao.DocRepository;
import com.yonsei.recommender.document.dto.DocSaveRequestDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DocSerivce {

    @NonNull
    private final DocRepository docRepository;

    @Transactional
    public void save(DocSaveRequestDto docDto) throws Exception {
        docRepository.save(docDto.toEntity());
    }

}
