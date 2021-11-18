/**
 * Dto responding a list of similar words
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.dto;

import kr.ac.yonsei.recommender.word.domain.SimilarityWord;
import kr.ac.yonsei.recommender.word.domain.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class SimilarityWordResponseDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class CdmWordDto {
        private String cdmWordId;

        private float floatSimilarity;

        private WordResponseDto detail;

        public CdmWordDto(SimilarityWord.CdmWord cdmWord) {
            this.cdmWordId = cdmWord.getCdmWordId().toHexString();
            this.floatSimilarity = cdmWord.getFloatSimilarity();
            this.detail = new WordResponseDto(cdmWord.getDetail());
        }
    }

    private String emrWordId;
    private List<CdmWordDto> cdmWordsList;

    public SimilarityWordResponseDto(SimilarityWord entity) {
        this.emrWordId = entity.getEmrWordId().toHexString();
        this.cdmWordsList = entity.getCdmWordsList().stream().map(CdmWordDto::new).collect(Collectors.toList());
    }
}
