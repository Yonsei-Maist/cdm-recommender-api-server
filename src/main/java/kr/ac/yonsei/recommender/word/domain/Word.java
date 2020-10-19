/**
 * Word entity
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

@Document("COL_WORD")
@NoArgsConstructor
@Getter
public class Word {

    @Id
    @Field("_id")
    private String id;

    @Field("str_text")
    private String word;

    @Field("bool_is_emr")
    private String emrExists;

    @Builder
    public Word(String id, String word, String emrExists) {
        this.id = id;
        this.word = word;
        this.emrExists = emrExists;
    }
}
