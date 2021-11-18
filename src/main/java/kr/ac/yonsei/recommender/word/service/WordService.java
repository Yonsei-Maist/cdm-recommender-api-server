/**
 * Work to convert EMR terms to CDM terms
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.service;

import kr.ac.yonsei.recommender.word.dao.SimilarityWordRepository;
import kr.ac.yonsei.recommender.word.dao.SynonymRepository;
import kr.ac.yonsei.recommender.word.dao.WordRepository;
import kr.ac.yonsei.recommender.word.domain.SimilarityWord;
import kr.ac.yonsei.recommender.word.domain.Synonym;
import kr.ac.yonsei.recommender.word.domain.Word;
import kr.ac.yonsei.recommender.word.dto.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@RequiredArgsConstructor
@Service
public class WordService {

    @NonNull
    private final WordRepository wordRepository;
    @Autowired
    private SimilarityWordRepository similarityWordRepository;
    @Autowired
    private SynonymRepository synonymRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Get a list of CDM similar words
     * @param id
     * @return SimilarityWordResponseDto
     * @throws Exception all of error
     */
    @Transactional(readOnly = true)
    public SimilarityWordResponseDto findByEmrWordId(String id, boolean isSynonym) throws Exception {
        Word word = wordRepository.findById(id).orElse(null);
        if (word == null) {
            throw new Exception("Wrong id:" + id);
        }

        List<Synonym> synonymList = synonymRepository.findByText(word.getWord());
        List<ObjectId> searchList = new ArrayList<>();
        if (synonymList.size() > 0) {
            for(Synonym synonym : synonymList) {
                searchList.add(new ObjectId(synonym.getEmrWordId().toHexString()));
            }
        } else {
            searchList.add(new ObjectId(id));
        }

        Aggregation aggregation = newAggregation(
                match(Criteria.where("id_word_emr").in(searchList))
                , unwind("arr_cdm_words")
                , lookup("COL_WORD", "arr_cdm_words.id_word_cdm", "_id", "arr_cdm_words.detail")
                , unwind("arr_cdm_words.detail")
                , group("_id")
                        .first("id_word_emr").as("id_word_emr")
                        .push("arr_cdm_words").as("arr_cdm_words")
        );

        List<SimilarityWord> result = mongoTemplate.aggregate(aggregation, "COL_SIMILARITY", SimilarityWord.class).getMappedResults();

        if (result.size() == 0) {
            return null;
        }

        SimilarityWord entity = result.get(0);

        return new SimilarityWordResponseDto(entity);
    }

    /**
     * Search word
     * @param word max 3 word sequences
     * @return WordResponseDto
     * @throws Exception all of error
     */
    @Transactional(readOnly = true)
    public WordResponseDto findById(String word) throws Exception {
//        Word entity = wordRepository.findByWord(word)
//                .orElseThrow(() -> new IllegalArgumentException("id="+word));

        ArrayList<String> searchList = new ArrayList<>();
        int maxLength = 3;

        // first, find max sequences
        searchList.add(word);

        String[] split = word.split("\\s");
        List<String> splitList = Arrays.asList(split);
        int total = split.length;
        for (int i = maxLength;i > 0 ;i--) {
            for (int j = 0; j < total - i + 1;j++) {
                searchList.addAll(splitList.subList(j, j + i));
            }
        }

        List<WordResponseDto> dtoList = wordRepository.findAllByIsEmrAndWordIn(true, searchList).stream().map(WordResponseDto::new).collect(Collectors.toList());

        if (dtoList.size() > 0) {

            dtoList.sort(((o1, o2) -> o2.getWord().length() - o1.getWord().length()));
            return dtoList.get(0);
        } else {
            return null;
        }
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
                match(Criteria.where("bool_is_emr").is(true))
                , skip((long)(pagingDto.getCurrentPageNo() - 1) * pagingDto.getRecordCountPerPage())
                , limit(pagingDto.getRecordCountPerPage())
                , lookup("COL_SYNONYM", "_id", "id_word_emr", "synonym")
                , unwind("synonym",false)
        );

        AggregationResults<Word> results = mongoTemplate.aggregate(aggregation, "COL_WORD", Word.class);
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
                match(Criteria.where("bool_is_emr").is(true)),
                group().count().as("count")
        );

        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "COL_WORD", Map.class);

        return Integer.parseInt(results.getMappedResults().get(0).get("count").toString());
    }

    /**
     * Initialization of CDM&EMR association list
     * @throws Exception all of error
     */
    @Transactional
    public void delete() throws Exception {
        wordRepository.deleteAll();
    }

    @Transactional
    public void createWord(WordCreateRequestDto dto) throws Exception {
        Word emr = wordRepository.findByWordAndIsEmr(dto.getEmrWord(), true).orElse(null);
        Word cdm = wordRepository.findById(dto.getCdmId()).orElse(null);
        SimilarityWord similarityWord = null;

        if (cdm == null) {
            throw new Exception("There are no CDM:" + dto.getCdmId());
        }

        if (emr == null) {
            //create emr word
            emr = Word.builder()
                    .word(dto.getEmrWord())
                    .isEmr(true)
                    .build();

            wordRepository.save(emr);
        } else {
            similarityWord = similarityWordRepository.findByEmrWordId(emr.getId()).orElse(null);
        }

        SimilarityWord.CdmWord cdmWord = new SimilarityWord.CdmWord();
        cdmWord.setCdmWordId(cdm.getId());
        cdmWord.setFloatSimilarity(1);
        if (similarityWord != null) {
            if (similarityWord.getCdmWordsList() != null) {
                similarityWord.getCdmWordsList().add(cdmWord);
            } else {
                ArrayList<SimilarityWord.CdmWord> cdmWordList = new ArrayList<>();
                cdmWordList.add(cdmWord);
                similarityWord.setCdmWordsList(cdmWordList);
            }
        } else {
            ArrayList<SimilarityWord.CdmWord> cdmWordList = new ArrayList<>();
            cdmWordList.add(cdmWord);
            similarityWord = SimilarityWord.builder()
                    .emrWordId(emr.getId())
                    .cdmWordsList(cdmWordList)
                    .build();
        }

        similarityWordRepository.save(similarityWord);
    }

    @Transactional
    public List<WordAllResponseDto> findAllCdmWords() {
        return wordRepository.findAllByIsEmrOrderByWordAsc(false).stream().map(WordAllResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void insertBatchFiles() throws Exception {
        ClassPathResource resource = new ClassPathResource("init/batch.txt");

        HashMap<String, Synonym> synonymList = new HashMap<>();
        File file = resource.getFile();
        FileInputStream stream = new FileInputStream(file);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\t");
                if (split.length < 2) {
                    System.out.println(line);
                    continue;
                }

                String word = split[1];
                String synonym = split[0];
                Word wordObj = wordRepository.findByWordAndIsEmrAndIsSynonym(word, true, false)
                        .orElse(null);
                Synonym synonymObj;
                if (wordObj == null) {
                    wordObj = Word.builder()
                            .word(word)
                            .isEmr(true)
                            .isSynonym(false)
                            .build();
                    wordRepository.save(wordObj);
                }

                Synonym.SynonymWord synonymWord = Synonym.SynonymWord.builder().word(synonym).build();

                Word synoynmWordObj = wordRepository.findByWordAndIsEmrAndIsSynonym(synonym, true, true)
                        .orElse(null);

                if (synoynmWordObj == null) {
                    synoynmWordObj = Word.builder()
                            .word(synonym)
                            .isEmr(true)
                            .isSynonym(true)
                            .build();
                    wordRepository.save(synoynmWordObj);
                }

                if (!synonymList.containsKey(word)) {
                    ArrayList<Synonym.SynonymWord> synonymWordList = new ArrayList<>();
                    synonymWordList.add(synonymWord);
                    synonymObj = Synonym.builder()
                            .emrWordId(wordObj.getId())
                            .synonymList(synonymWordList)
                            .build();

                    synonymList.put(word, synonymObj);
                } else {
                    synonymObj = synonymList.get(word);
                    synonymObj.getSynonymList().add(synonymWord);
                }
            }
        }

        synonymRepository.saveAll(synonymList.values());
    }
}
