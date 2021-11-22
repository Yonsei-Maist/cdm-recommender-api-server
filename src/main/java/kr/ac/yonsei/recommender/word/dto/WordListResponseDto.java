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
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class WordListResponseDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SynonymDto {
        private String emrWordId; //FK(COL_WORD)
        private ArrayList<Synonym.SynonymWord> synonymList;

        public SynonymDto(Synonym synonym) {
            this.emrWordId = synonym.getEmrWordId().toHexString();
            this.synonymList = synonym.getSynonymList();
        }
    }

    private String id;
    private String word;
    private boolean isEmr;
    private SynonymDto synonym;

    public WordListResponseDto(Word entity){
        this.id = entity.getId().toHexString();
        this.word = entity.getWord();
        this.isEmr = entity.isEmr();
        this.synonym = new SynonymDto(entity.getSynonym());
    }

}
