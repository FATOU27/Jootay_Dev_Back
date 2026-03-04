package com.fd.Jootay_Dev.reporitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fd.Jootay_Dev.enums.Difficulty;
import com.fd.Jootay_Dev.enums.QuestionCategory;
import com.fd.Jootay_Dev.model.Question;

@SuppressWarnings("java:S2176")
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(QuestionCategory questioncategory);

    List<Question> findByDifficulty(Difficulty difficulty);

    List<Question> findByCategoryAndDifficulty(QuestionCategory questionCategory, Difficulty difficulty);

}
