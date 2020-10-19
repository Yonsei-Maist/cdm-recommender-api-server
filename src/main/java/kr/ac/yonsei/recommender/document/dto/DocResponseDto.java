/**
 * Dto responding EMR documents
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.document.dto;

import kr.ac.yonsei.recommender.document.domain.Doc;
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
