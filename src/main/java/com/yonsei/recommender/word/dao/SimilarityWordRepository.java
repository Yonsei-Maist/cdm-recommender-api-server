package com.yonsei.recommender.word.dao;

import com.yonsei.recommender.word.domain.SimilarityWord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SimilarityWordRepository extends MongoRepository<SimilarityWord, String> {

    Optional<SimilarityWord> findByEmrWordId(String id);
}
