package kr.ac.yonsei.recommender.word.dao;

import kr.ac.yonsei.recommender.word.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordRepository extends MongoRepository<Word, String> {

    int countAllBy();

    Page<Word> findAllByOrderByWordAsc(Pageable pageable);
}
