/**
 * Work to convert EMR terms to CDM terms
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.service;

import kr.ac.yonsei.recommender.word.dao.SimilarityWordRepository;
import kr.ac.yonsei.recommender.word.dao.WordRepository;
import kr.ac.yonsei.recommender.word.domain.SimilarityWord;
import kr.ac.yonsei.recommender.word.dto.PagingDto;
import kr.ac.yonsei.recommender.word.dto.SimilarityWordResponseDto;
import kr.ac.yonsei.recommender.word.dto.WordListResponseDto;
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

    /**
     * Get a list of CDM similar words
     * @param id
     * @return SimilarityWordResponseDto
     * @throws Exception all of error
     */
    @Transactional(readOnly = true)
    public SimilarityWordResponseDto findByEmrWordId(String id) throws Exception {
        SimilarityWord entity = similarityWordRepository.findByEmrWordId(id)
                .orElseThrow(() -> new IllegalArgumentException("id="+id));

        return new SimilarityWordResponseDto(entity);
    }

    /**
     * Get a list of CDM&EMR association relationship (paging)
     * @param pagingDto PagingDto
     * @return List<WordListResponseDto>
     * @throws Exception all of error
     */
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

    /**
     * Get the count of CDM&EMR association relationship
     * @return count
     * @throws Exception all of error
     */
    @Transactional(readOnly = true)
    public int countAll() throws Exception {
        return wordRepository.countAllBy();
    }

    /**
     * Initialization of CDM&EMR association list
     * @throws Exception all of error
     */
    @Transactional
    public void delete() throws Exception {
        wordRepository.deleteAll();
    }

}
