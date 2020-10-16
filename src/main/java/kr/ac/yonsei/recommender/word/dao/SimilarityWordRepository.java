package kr.ac.yonsei.recommender.word.dao;

import kr.ac.yonsei.recommender.word.domain.SimilarityWord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SimilarityWordRepository extends MongoRepository<SimilarityWord, String> {

    Optional<SimilarityWord> findByEmrWordId(String id);
}
