/**
 * Dto responding a list of EMR documents
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
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
