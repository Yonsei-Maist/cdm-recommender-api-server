/**
 * Dto responding a list of CDM&EMR association relationship
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.dto;

import kr.ac.yonsei.recommender.word.domain.Word;
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
