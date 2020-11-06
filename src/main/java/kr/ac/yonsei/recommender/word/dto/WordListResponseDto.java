/**
 * Dto responding a list of CDM&EMR association relationship
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.dto;

import kr.ac.yonsei.recommender.word.domain.Synonym;
import kr.ac.yonsei.recommender.word.domain.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class WordListResponseDto {

    private String id;
    private String word;
    private String emrExists;
    private Synonym synonym;

    public WordListResponseDto(Word entity){
        this.id = entity.getId();
        this.word = entity.getWord();
        this.emrExists = entity.getEmrExists();
        this.synonym = entity.getSynonym();
    }

}
