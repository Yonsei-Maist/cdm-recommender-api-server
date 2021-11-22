package kr.ac.yonsei.recommender.word.dto;

import kr.ac.yonsei.recommender.word.domain.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Getter
@NoArgsConstructor
public class WordResponseDto {

    private String id;
    private String word;
    private boolean emr;
    private boolean synonym;

    public WordResponseDto(Word entity){
        this.id = entity.getId().toHexString();
        this.word = entity.getWord();
        this.emr = entity.isEmr();
        this.synonym = entity.isSynonym();
    }
}
