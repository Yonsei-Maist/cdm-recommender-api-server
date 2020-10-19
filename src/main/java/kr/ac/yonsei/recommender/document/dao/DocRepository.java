/**
 * Generate SQL by mapping COL_DOC(mongoDB) and doc entity
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.document.dao;

import kr.ac.yonsei.recommender.document.domain.Doc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DocRepository extends MongoRepository<Doc, String> {

    List<Doc> findAllDescByUserId(String userId);
}
