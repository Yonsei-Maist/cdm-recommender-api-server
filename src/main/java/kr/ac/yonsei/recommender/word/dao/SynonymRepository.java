/**
 * Generate SQL by mapping COL_WORD(mongoDB) and word entity
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.dao;

import kr.ac.yonsei.recommender.word.domain.Synonym;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SynonymRepository extends MongoRepository<Synonym, String> {

    @org.springframework.data.mongodb.repository.Query(value = "{ 'arr_synonym': { $elemMatch: { 'str_text' : ?0 } }}")
    List<Synonym> findByText(String id);
}
