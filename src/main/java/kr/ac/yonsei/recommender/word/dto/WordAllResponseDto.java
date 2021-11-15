package kr.ac.yonsei.recommender.word.dto;

import kr.ac.yonsei.recommender.word.domain.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WordAllResponseDto {
    private String word;
    private boolean isEmr;
    private String id;

    public WordAllResponseDto(Word cdm) {
        this.word = cdm.getWord();
        this.id = cdm.getId();
        isEmr = cdm.isEmr();
    }
}
