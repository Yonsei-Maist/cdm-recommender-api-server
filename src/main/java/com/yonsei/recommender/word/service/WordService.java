package com.yonsei.recommender.word.service;

import com.yonsei.recommender.word.dao.SimilarityWordRepository;
import com.yonsei.recommender.word.dao.WordRepository;
import com.yonsei.recommender.word.domain.SimilarityWord;
import com.yonsei.recommender.word.dto.PagingDto;
import com.yonsei.recommender.word.dto.SimilarityWordResponseDto;
import com.yonsei.recommender.word.dto.WordListResponseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WordService {

    @NonNull
    private final SimilarityWordRepository similarityWordRepository;
    @NonNull
    private final WordRepository wordRepository;

    @Transactional(readOnly = true)
    public SimilarityWordResponseDto findByEmrWordId(String id) throws Exception {
        SimilarityWord entity = similarityWordRepository.findByEmrWordId(id)
                .orElseThrow(() -> new IllegalArgumentException("id="+id));

        return new SimilarityWordResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<WordListResponseDto> findAll(PagingDto pagingDto) throws Exception {
        Pageable paging = PageRequest.of(pagingDto.getCurrentPageNo()-1, pagingDto.getRecordCountPerPage());

        List<WordListResponseDto> wordList = wordRepository
                .findAllByOrderByWordAsc(paging)
                .stream()
                .map(WordListResponseDto::new)
                .collect(Collectors.toList());

        return wordList;
    }

    @Transactional(readOnly = true)
    public int countAll() throws Exception {
        return wordRepository.countAllBy();
    }

    @Transactional
    public void delete() throws Exception {
        wordRepository.deleteAll();
    }

}
