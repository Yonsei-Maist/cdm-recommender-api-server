/**
 * Work to convert EMR terms to CDM terms
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.controller;

import kr.ac.yonsei.recommender.global.common.ResponseMessage;
import kr.ac.yonsei.recommender.word.dto.PagingDto;
import kr.ac.yonsei.recommender.word.dto.SimilarityWordRequestDto;
import kr.ac.yonsei.recommender.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WordController {

    private final WordService wordService;

    /**
     * Get a list of CDM similar words
     * @param dto SimilarityWordRequestDto
     * @return Response message, SimilarityWordResponseDto
     * @throws Exception all of error
     */
    @PostMapping("/cdm/similarity/words")
    public ResponseEntity<ResponseMessage> findByEmrWordId(@RequestBody SimilarityWordRequestDto dto) throws Exception {
        ResponseMessage responseMessage = ResponseMessage.builder()
                .data(wordService.findByEmrWordId(dto.getWord()))
                .build();
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }

    /**
     * Get a list of CDM&EMR association relationship (paging)
     * @param pagingDto PagingDto
     * @return Response message, List<WordListResponseDto>
     * @throws Exception all of error
     */
    @PostMapping("/cdm/words/list")
    public ResponseEntity<ResponseMessage> findAll(@RequestBody PagingDto pagingDto) throws Exception {
        int totalRecordCount = wordService.countAll();
        pagingDto.setTotalRecordCount(totalRecordCount);

        if(totalRecordCount!=0){
            ResponseMessage responseMessage = ResponseMessage.builder()
                    .data(wordService.findAll(pagingDto))
                    .build();
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(), HttpStatus.OK);
        }
    }

    /**
     * Initialization of CDM&EMR association list
     * @return Response message
     * @throws Exception all of error
     */
    @PostMapping("/cdm/words")
    public ResponseEntity<ResponseMessage> delete() throws Exception {
        wordService.delete();
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(), HttpStatus.OK);
    }

}
