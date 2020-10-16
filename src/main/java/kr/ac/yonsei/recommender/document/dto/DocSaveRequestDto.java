package kr.ac.yonsei.recommender.document.dto;

import kr.ac.yonsei.recommender.document.domain.Doc;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@NoArgsConstructor
public class DocSaveRequestDto {

    @NotEmpty
    private String userId;
    private String title;
    private Object content;

    DocSaveRequestDto(@NotEmpty String userId, String title, String content){
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Doc toEntity() {
        return Doc.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}