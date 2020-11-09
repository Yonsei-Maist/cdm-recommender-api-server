/**
 * Generate SQL by mapping COL_WORD(mongoDB) and word entity
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.word.dao;

import kr.ac.yonsei.recommender.word.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WordRepository extends MongoRepository<Word, String> {

    int countAllBy();

    Optional<Word> findByWord(String word);

    Page<Word> findAllByOrderByWordAsc(Pageable pageable);
}
