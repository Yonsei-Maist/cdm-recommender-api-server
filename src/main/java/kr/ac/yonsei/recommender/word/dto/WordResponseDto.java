package kr.ac.yonsei.recommender.word.dto;

import kr.ac.yonsei.recommender.word.domain.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WordResponseDto {

    private String id;
    private String word;
    private boolean emrExists;

    public WordResponseDto(Word entity){
        this.id = entity.getId();
        this.word = entity.getWord();
        this.emrExists = entity.isEmr();
    }
}
