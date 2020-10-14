package com.yonsei.recommender.word.dao;

import com.yonsei.recommender.word.domain.Word;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordRepository extends MongoRepository<Word, String> {

    Word findByEmrWordId(String id);
}
