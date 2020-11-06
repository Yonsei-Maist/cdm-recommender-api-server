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
    private String id; //PK emrWordId

    @Field("str_text")
    private String word;

    @Field("bool_is_emr")
    private String emrExists;

    @Field("synonym")
    private Synonym synonym;

    @Builder
    public Word(String id, String word, String emrExists, Synonym synonym) {
        this.id = id;
        this.word = word;
        this.emrExists = emrExists;
        this.synonym = synonym;
    }
}
