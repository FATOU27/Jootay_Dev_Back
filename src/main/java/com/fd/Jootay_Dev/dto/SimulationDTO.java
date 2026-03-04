package com.fd.Jootay_Dev.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fd.Jootay_Dev.enums.QuestionCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SimulationDTO {

    // ---- Requête pour sauvegarder une session ----
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SaveSessionRequest {
        private QuestionCategory category;
        private int score;
        private int totalQuestions;
        private long durationSeconds;
        private List<Long> unknownQuestionIds; // IDs des questions ratées
    }

    // ---- Réponse session ----
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SessionResponse {
        private Long id;
        private QuestionCategory category;
        private int score;
        private int totalQuestions;
        private int percentage;
        private long durationSeconds;
        private LocalDateTime createdAt;
        private List<QuestionDTO> unknownQuestions; // détail des questions ratées
    }
}
