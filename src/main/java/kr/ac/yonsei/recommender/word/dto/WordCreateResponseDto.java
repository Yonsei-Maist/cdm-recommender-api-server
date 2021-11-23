package kr.ac.yonsei.recommender.word.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordCreateResponseDto {
    private String emrWord;
    private String emrId;
    private String cdmWord;
    private String cdmId;
}
