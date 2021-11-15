/**
 * Generate SQL by mapping COL_WORD(mongoDB) and word entity
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.dao;

import kr.ac.yonsei.recommender.word.domain.Synonym;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SynonymRepository extends MongoRepository<Synonym, String> {

}
