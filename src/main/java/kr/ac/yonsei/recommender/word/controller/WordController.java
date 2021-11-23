/**
 * Work to convert EMR terms to CDM terms
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.controller;

import kr.ac.yonsei.recommender.global.common.ResponseMessage;
import kr.ac.yonsei.recommender.word.dto.*;
import kr.ac.yonsei.recommender.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WordController {

    private final WordService wordService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Get a list of CDM similar words
     * @param dto SimilarityWordRequestDto
     * @return Response message, SimilarityWordResponseDto
     * @throws Exception all of error
     */
    @PostMapping("/cdm/similarity/words")
    public ResponseEntity<ResponseMessage> findByEmrWordId(@RequestBody SimilarityWordRequestDto dto) throws Exception {
        ResponseMessage responseMessage = ResponseMessage.builder()
                .data(wordService.findByEmrWordId(dto.getId()))
                .build();
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }

    /**
     * Search word
     * @param dto WordRequestDto
     * @return Response message, WordResponseDto
     * @throws Exception all of error
     */
    @PostMapping("/cdm/words")
    public ResponseEntity<ResponseMessage> findById(@RequestBody WordRequestDto dto) throws Exception {
        ResponseMessage responseMessage = ResponseMessage.builder()
                .data(wordService.findById(dto.getWord()))
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
        List<WordListResponseDto> wordList = wordService.findAll(pagingDto);

        JSONObject obj =new JSONObject();
        obj.put("recordCountPerPage", pagingDto.getRecordCountPerPage());
        obj.put("wordList", wordList);
        obj.put("totalRecordCount", totalRecordCount);

        if(totalRecordCount!=0){
            ResponseMessage responseMessage = ResponseMessage.builder()
                    .data(obj)
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
    @PostMapping("/cdm/words/init")
    public ResponseEntity<ResponseMessage> delete() throws Exception {

/*        String id = "";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://165.132.221.44:8998/cdm/ai/init").queryParam("id", id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseMessage responseMessage = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, ResponseMessage.class).getBody();*/

        wordService.delete();

       return new ResponseEntity<ResponseMessage>(new ResponseMessage(), HttpStatus.OK);
    }

    /**
     * Analysis and save of CDM&EMR association list
     * @return Response message
     * @throws Exception all of error
     */
    @PostMapping("/cdm/words/analysis")
    public ResponseEntity<ResponseMessage> analyze() throws Exception {

        String id = "";
/*
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://165.132.221.44:8998/cdm/ai/start").queryParam("id", id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseMessage responseMessage = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, ResponseMessage.class).getBody();

*/
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(), HttpStatus.OK);
    }

    @PostMapping("/cdm/similarity/create")
    public ResponseEntity<ResponseMessage> createEmrCdmWordPair(@RequestBody WordCreateRequestDto dto) throws Exception {

        ResponseMessage responseMessage = ResponseMessage.builder()
                .data(this.wordService.createWord(dto))
                .build();
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/cdm/all")
    public ResponseEntity<ResponseMessage> findCdmAll() throws Exception {
        ResponseMessage message = ResponseMessage.builder().data(this.wordService.findAllCdmWords()).build();
        return new ResponseEntity<ResponseMessage>(message, HttpStatus.OK);
    }

    @PostMapping("/cdm/batch")
    public ResponseEntity<ResponseMessage> batchAll() throws Exception {
        this.wordService.insertBatchFiles();
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(), HttpStatus.OK);
    }
}
