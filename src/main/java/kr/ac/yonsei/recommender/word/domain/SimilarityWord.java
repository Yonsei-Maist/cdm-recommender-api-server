/**
 * Similarity word entity
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Document("COL_SIMILARITY")
@NoArgsConstructor
@Getter
public class SimilarityWord {

    @Id
    @Field("_id")
    private String id;

    @Field("id_word_emr")
    private String emrWordId;

    @Field("arr_cdm_words")
    private ArrayList<CdmWord> cdmWordsList;


    @NoArgsConstructor
    @Getter
    public class CdmWord {

        @Field("id_word_cdm")
        private String cdmWordId;

        @Field("float_similarity")
        private float floatSimilarity;

    }

    @Builder
    public SimilarityWord(String id, String emrWordId, ArrayList<CdmWord> cdmWordsList) {
        this.id = id;
        this.emrWordId = emrWordId;
        this.cdmWordsList = cdmWordsList;
    }
}
