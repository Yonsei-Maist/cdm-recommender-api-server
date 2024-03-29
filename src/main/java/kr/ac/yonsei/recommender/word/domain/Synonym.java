package kr.ac.yonsei.recommender.word.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Document(collection = "COL_SYNONYM")
@NoArgsConstructor
@Getter
public class Synonym {

    @Field("id_word_emr")
    private ObjectId emrWordId; //FK(COL_WORD)

    @Field("arr_synonym")
    private ArrayList<SynonymWord> synonymList;

    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class SynonymWord {

        @Field("str_text")
        private String word;

        @Field("id_reference")
        private ArrayList referenceId; //FK
    }

    @Builder
    public Synonym(ObjectId emrWordId, ArrayList synonymList) {
        this.emrWordId = emrWordId;
        this.synonymList = synonymList;
    }
}
