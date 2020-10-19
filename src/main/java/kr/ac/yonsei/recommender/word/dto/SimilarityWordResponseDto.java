/**
 * Dto responding a list of similar words
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.dto;

import kr.ac.yonsei.recommender.word.domain.SimilarityWord;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SimilarityWordResponseDto {

    private String emrWordId;
    private Object cdmWordsList;

    public SimilarityWordResponseDto(SimilarityWord entity) {
        this.emrWordId = entity.getEmrWordId();
        this.cdmWordsList = entity.getCdmWordsList();
    }
}
