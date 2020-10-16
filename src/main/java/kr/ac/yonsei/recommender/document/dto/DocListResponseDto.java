package kr.ac.yonsei.recommender.document.dto;

import kr.ac.yonsei.recommender.document.domain.Doc;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DocListResponseDto {

    private String id;
    private String title;

    public DocListResponseDto(Doc entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
    }
}
