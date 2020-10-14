package com.yonsei.recommender.document.dto;

import com.yonsei.recommender.document.domain.Doc;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DocResponseDto {

    private String id;
    private String title;
    private Object content;

    public DocResponseDto(Doc entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
