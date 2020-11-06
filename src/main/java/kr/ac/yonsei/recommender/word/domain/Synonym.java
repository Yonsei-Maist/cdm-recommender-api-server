package kr.ac.yonsei.recommender.word.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Document(collection = "COL_SYNONYM")
@NoArgsConstructor
@Getter
public class Synonym {

    @Field("id_word_emr")
    private String emrWordId; //FK(COL_WORD)

    @Field("arr_synonym")
    private ArrayList<SynonymWord> synonymList;

    @NoArgsConstructor
    @Getter
    public class SynonymWord {

        @Field("str_text")
        private String word;

        @Field("id_reference")
        private String referenceId; //FK
    }

    @Builder
    public Synonym(String emrWordId, ArrayList synonymList) {
        this.emrWordId = emrWordId;
        this.synonymList = synonymList;
    }
}
