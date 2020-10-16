package kr.ac.yonsei.recommender.word.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    private Object cdmWordsList;

    @Builder
    public SimilarityWord(String id, String emrWordId, Object cdmWordsList) {
        this.id = id;
        this.emrWordId = emrWordId;
        this.cdmWordsList = cdmWordsList;
    }
}
