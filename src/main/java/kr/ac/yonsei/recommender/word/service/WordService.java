/**
 * Work to convert EMR terms to CDM terms
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.service;

import kr.ac.yonsei.recommender.word.dao.WordRepository;
import kr.ac.yonsei.recommender.word.domain.SimilarityWord;
import kr.ac.yonsei.recommender.word.domain.Word;
import kr.ac.yonsei.recommender.word.dto.PagingDto;
import kr.ac.yonsei.recommender.word.dto.SimilarityWordResponseDto;
import kr.ac.yonsei.recommender.word.dto.WordListResponseDto;
import kr.ac.yonsei.recommender.word.dto.WordResponseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@RequiredArgsConstructor
@Service
public class WordService {

    @NonNull
    private final WordRepository wordRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Get a list of CDM similar words
     * @param id
     * @return SimilarityWordResponseDto
     * @throws Exception all of error
     */
    @Transactional(readOnly = true)
    public SimilarityWordResponseDto findByEmrWordId(String id) throws Exception {

        Aggregation aggregation = newAggregation(
                match(Criteria.where("id_word_emr").is(id))
                , unwind("arr_cdm_words")
                , lookup("COL_WORD", "arr_cdm_words.id_word_cdm", "_id", "arr_cdm_words.detail")
                , unwind("arr_cdm_words.detail")
                , group("_id")
                        .first("id_word_emr").as("id_word_emr")
                        .push("arr_cdm_words").as("arr_cdm_words")
        );

        List<SimilarityWord> result = mongoTemplate.aggregate(aggregation, "COL_SIMILARITY", SimilarityWord.class).getMappedResults();
        SimilarityWord entity = result.get(0);

        return new SimilarityWordResponseDto(entity);
    }

    /**
     * Search word
     * @param word
     * @return WordResponseDto
     * @throws Exception all of error
     */
    @Transactional(readOnly = true)
    public WordResponseDto findById(String word) throws Exception {

        Word entity = wordRepository.findByWord(word)
                .orElseThrow(() -> new IllegalArgumentException("id="+word));

        return new WordResponseDto(entity);
    }

    /**
     * Get a list of CDM&EMR association relationship (paging)
     * @param pagingDto PagingDto
     * @return List<WordListResponseDto>
     * @throws Exception all of error
     */
    @Transactional(readOnly = true)
    public List<WordListResponseDto> findAll(PagingDto pagingDto) throws Exception {

        Aggregation aggregation = newAggregation(
                lookup("COL_SYNONYM", "_id", "id_word_emr", "synonym")
                , unwind("synonym",false)
                , skip((pagingDto.getCurrentPageNo()-1) * pagingDto.getRecordCountPerPage())
                , limit(pagingDto.getRecordCountPerPage())
        );

        AggregationResults results = mongoTemplate.aggregate(aggregation, "COL_WORD", Word.class);
        List<Word> list = results.getMappedResults();

        List<WordListResponseDto> wordList = list
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

        Aggregation aggregation = newAggregation(
                lookup("COL_SYNONYM", "_id", "id_word_emr", "synonym")
                , unwind("synonym",false)
        );

        AggregationResults results = mongoTemplate.aggregate(aggregation, "COL_WORD", Word.class);
        List<Word> list = results.getMappedResults();

        List<WordListResponseDto> wordList = list
                .stream()
                .map(WordListResponseDto::new)
                .collect(Collectors.toList());

        return wordList.size();
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
