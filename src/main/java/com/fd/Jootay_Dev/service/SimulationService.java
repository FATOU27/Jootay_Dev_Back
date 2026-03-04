package com.fd.Jootay_Dev.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fd.Jootay_Dev.dto.QuestionDTO;
import com.fd.Jootay_Dev.dto.SimulationDTO;
import com.fd.Jootay_Dev.enums.ProgressStatus;
import com.fd.Jootay_Dev.enums.QuestionCategory;
import com.fd.Jootay_Dev.model.Question;
import com.fd.Jootay_Dev.model.SimulationSession;
import com.fd.Jootay_Dev.reporitory.QuestionRepository;
import com.fd.Jootay_Dev.reporitory.SimulationSessionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SimulationService {

    private final QuestionRepository questionRepository;
    private final SimulationSessionRepository sessionRepository;
    private final UserProgressService progressService;

    // ---- Récupérer N questions aléatoires ----

    public List<QuestionDTO> getRandomQuestions(QuestionCategory category, int count) {
        List<Question> questions = (category != null)
                ? questionRepository.findByCategory(category)
                : questionRepository.findAll();

        Collections.shuffle(questions);

        return questions.stream()
                .limit(count)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ---- Sauvegarder une session ----

    public SimulationDTO.SessionResponse saveSession(SimulationDTO.SaveSessionRequest request) {
        // Sauvegarder la session
        SimulationSession session = SimulationSession.builder()
                .category(request.getCategory())
                .score(request.getScore())
                .totalQuestions(request.getTotalQuestions())
                .durationSeconds(request.getDurationSeconds())
                .build();

        session = sessionRepository.save(session);

        // Marquer les questions ratées dans la progression
        if (request.getUnknownQuestionIds() != null) {
            request.getUnknownQuestionIds().forEach(qId -> progressService.setStatus(qId, ProgressStatus.UNKNOWN));
        }

        // Récupérer le détail des questions ratées
        List<QuestionDTO> unknownQuestions = request.getUnknownQuestionIds() == null
                ? List.of()
                : request.getUnknownQuestionIds().stream()
                        .map(id -> questionRepository.findById(id).map(this::toDTO).orElse(null))
                        .filter(q -> q != null)
                        .collect(Collectors.toList());

        int percentage = request.getTotalQuestions() > 0
                ? (request.getScore() * 100) / request.getTotalQuestions()
                : 0;

        return SimulationDTO.SessionResponse.builder()
                .id(session.getId())
                .category(session.getCategory())
                .score(session.getScore())
                .totalQuestions(session.getTotalQuestions())
                .percentage(percentage)
                .durationSeconds(session.getDurationSeconds())
                .createdAt(session.getCreatedAt())
                .unknownQuestions(unknownQuestions)
                .build();
    }

    // ---- Historique des sessions ----

    public List<SimulationDTO.SessionResponse> getHistory() {
        return sessionRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(s -> SimulationDTO.SessionResponse.builder()
                        .id(s.getId())
                        .category(s.getCategory())
                        .score(s.getScore())
                        .totalQuestions(s.getTotalQuestions())
                        .percentage(s.getTotalQuestions() > 0
                                ? (s.getScore() * 100) / s.getTotalQuestions()
                                : 0)
                        .durationSeconds(s.getDurationSeconds())
                        .createdAt(s.getCreatedAt())
                        .unknownQuestions(List.of())
                        .build())
                .collect(Collectors.toList());
    }

    // ---- Mapper ----

    private QuestionDTO toDTO(Question q) {
        return QuestionDTO.builder()
                .id(q.getId())
                .title(q.getTitle())
                .answer(q.getAnswer())
                .codeExample(q.getCodeExample())
                .difficulty(q.getDifficulty())
                .category(q.getCategory())
                .build();
    }
}
