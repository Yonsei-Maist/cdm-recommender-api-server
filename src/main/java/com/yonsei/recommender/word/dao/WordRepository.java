package com.yonsei.recommender.word.dao;

import com.yonsei.recommender.word.domain.Word;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WordRepository extends MongoRepository<Word, String> {

    Optional<Word> findByEmrWordId(String id);
}
