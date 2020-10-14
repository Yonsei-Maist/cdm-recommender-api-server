package com.yonsei.recommender.document.dto;

import com.yonsei.recommender.document.domain.Doc;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
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