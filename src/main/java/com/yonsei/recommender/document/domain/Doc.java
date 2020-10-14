package com.yonsei.recommender.document.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("COL_DOC")
@NoArgsConstructor
@Getter
public class Doc {

    @Id
    @Field("_id")
    private String id;

    @Field("id_user")
    private String userId;

    @Field("obj_content")
    private Object content;

    @Field("str_title")
    private String title;

    @Builder
    public Doc(String userId, Object content, String title) {
        this.userId = userId;
        this.content = content;
        this.title = title;
    }
}
