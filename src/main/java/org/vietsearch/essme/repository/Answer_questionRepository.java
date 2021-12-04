package org.vietsearch.essme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.vietsearch.essme.model.answer_question.Question;

public interface Answer_questionRepository extends MongoRepository<Question,String> {
    Page<Question> findByTopic(String topic, Pageable pageable);
}
