package kr.ac.yonsei.recommender.document.dao;

import kr.ac.yonsei.recommender.document.domain.Doc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DocRepository extends MongoRepository<Doc, String> {

    List<Doc> findAllDescByUserId(String userId);
}
