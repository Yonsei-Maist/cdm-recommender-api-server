package kr.ac.yonsei.recommender.word.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WordCreateRequestDto {
    private String emrWord;
    private String cdmId;
}
