package com.fd.Jootay_Dev.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fd.Jootay_Dev.enums.ProgressStatus;
import com.fd.Jootay_Dev.enums.QuestionCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ---- Réponse après sauvegarde ----
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProgressDTO {
    private Long questionId;
    private ProgressStatus status;
    private LocalDateTime updatedAt;

    // ---- Stats par catégorie ----
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CategoryStats {
        private QuestionCategory category;
        private long total;
        private long known;
        private long unknown;
        private int percentage;
    }

    // ---- Stats globales ----
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GlobalStats {
        private long total;
        private long known;
        private long unknown;
        private long untouched;
        private List<CategoryStats> byCategory;
    }
}