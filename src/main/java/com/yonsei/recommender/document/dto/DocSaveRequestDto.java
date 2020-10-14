package com.yonsei.recommender.document.dto;

import com.yonsei.recommender.document.domain.Doc;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class DocSaveRequestDto {

    private String userId;
    private String title;
    private Object content;

    public Doc toEntity() {
        return Doc.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}