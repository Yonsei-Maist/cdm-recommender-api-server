/**
 * Generate SQL by mapping COL_SIMILARITY(mongoDB) and similarityWord entity
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.dao;

import kr.ac.yonsei.recommender.word.domain.SimilarityWord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SimilarityWordRepository extends MongoRepository<SimilarityWord, String> {

    Optional<SimilarityWord> findByEmrWordId(String id);
}
