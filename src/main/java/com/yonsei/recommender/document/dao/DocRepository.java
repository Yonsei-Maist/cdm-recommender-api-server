package com.yonsei.recommender.document.dao;

import com.yonsei.recommender.document.domain.Doc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocRepository extends MongoRepository<Doc, String> {
}
