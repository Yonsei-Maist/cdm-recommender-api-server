package com.yonsei.recommender.word.dto;

import com.yonsei.recommender.word.domain.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WordResponseDto {

    private String id;
    private String emrWordId;
    private Object cdmWordsList;

    public WordResponseDto(Word entity) {
        this.id = entity.getId();
        this.emrWordId = entity.getEmrWordId();
        this.cdmWordsList = entity.getCdmWordsList();
    }
}
