/**
 * Word entity
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Document("COL_WORD")
@NoArgsConstructor
@Getter
@Setter
public class Word {

    @Id
    @Field("_id")
    private ObjectId id; //PK emrWordId

    @Field("str_text")
    private String word;

    @Field("bool_is_emr")
    private boolean isEmr;

    @Field("bool_is_synonym")
    private boolean isSynonym;

    @Field("synonym")
    private Synonym synonym;

    @Builder
    public Word(ObjectId id, String word, boolean isEmr, boolean isSynonym, Synonym synonym) {
        this.id = id;
        this.word = word;
        this.isEmr = isEmr;
        this.isSynonym = isSynonym;
        this.synonym = synonym;
    }
}
