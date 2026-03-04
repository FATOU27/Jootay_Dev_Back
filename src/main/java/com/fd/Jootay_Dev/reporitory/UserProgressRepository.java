package com.fd.Jootay_Dev.reporitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fd.Jootay_Dev.enums.ProgressStatus;
import com.fd.Jootay_Dev.enums.QuestionCategory;
import com.fd.Jootay_Dev.model.UserProgress;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {

    Optional<UserProgress> findByQuestionId(Long questionId);

    List<UserProgress> findByStatus(ProgressStatus status);

    // ✅ Après — passe directement le QuestionCategory
    @Query("""
                SELECT p FROM UserProgress p
                JOIN Question q ON q.id = p.questionId
                WHERE q.category = :category
            """)
    List<UserProgress> findByCategory(QuestionCategory category);

    long countByStatus(ProgressStatus status);
}
