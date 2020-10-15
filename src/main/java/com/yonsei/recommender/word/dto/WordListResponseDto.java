package com.yonsei.recommender.word.dto;

import com.yonsei.recommender.word.domain.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WordListResponseDto {

    private String id;
    private String word;
    private String emrExists;

    public WordListResponseDto(Word entity){
        this.id = entity.getId();
        this.word = entity.getWord();
        this.emrExists = entity.getEmrExists();
    }
}
