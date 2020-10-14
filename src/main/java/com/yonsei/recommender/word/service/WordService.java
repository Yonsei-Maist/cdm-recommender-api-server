package com.yonsei.recommender.word.service;

import com.yonsei.recommender.word.dao.WordRepository;
import com.yonsei.recommender.word.domain.Word;
import com.yonsei.recommender.word.dto.WordResponseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WordService {

    @NonNull
    private final WordRepository wordRepository;

    @Transactional(readOnly = true)
    public WordResponseDto findByEmrWordId(String id) throws Exception {

        Word entity = wordRepository.findByEmrWordId(id);
        return new WordResponseDto(entity);
    }
}
