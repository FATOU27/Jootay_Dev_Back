package com.fd.Jootay_Dev.reporitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fd.Jootay_Dev.enums.QuestionCategory;
import com.fd.Jootay_Dev.model.SimulationSession;

@Repository
public interface SimulationSessionRepository extends JpaRepository<SimulationSession, Long> {

    List<SimulationSession> findAllByOrderByCreatedAtDesc();

    List<SimulationSession> findByCategoryOrderByCreatedAtDesc(QuestionCategory category);
}