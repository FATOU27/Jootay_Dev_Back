package com.fd.Jootay_Dev.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fd.Jootay_Dev.dto.UserProgressDTO;
import com.fd.Jootay_Dev.enums.ProgressStatus;
import com.fd.Jootay_Dev.enums.QuestionCategory;
import com.fd.Jootay_Dev.model.UserProgress;
import com.fd.Jootay_Dev.reporitory.QuestionRepository;
import com.fd.Jootay_Dev.reporitory.UserProgressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProgressService {

    private final UserProgressRepository progressRepository;
    private final QuestionRepository questionRepository;

    // ---- Enregistrer / mettre à jour le statut ----

    public UserProgressDTO.GlobalStats setStatus(Long questionId, ProgressStatus status) {
        UserProgress progress = progressRepository.findByQuestionId(questionId)
                .orElse(UserProgress.builder().questionId(questionId).build());

        progress.setStatus(status);
        progressRepository.save(progress);

        return getStats();
    }

    // ---- Stats globales ----

    public UserProgressDTO.GlobalStats getStats() {
        long totalQuestions = questionRepository.count();
        long known = progressRepository.countByStatus(ProgressStatus.KNOWN);
        long unknown = progressRepository.countByStatus(ProgressStatus.UNKNOWN);
        long untouched = totalQuestions - known - unknown;

        List<UserProgressDTO.CategoryStats> byCategory = Arrays.stream(QuestionCategory.values())
                .map(cat -> buildCategoryStats(cat))
                .collect(Collectors.toList());

        return UserProgressDTO.GlobalStats.builder()
                .total(totalQuestions)
                .known(known)
                .unknown(unknown)
                .untouched(untouched)
                .byCategory(byCategory)
                .build();
    }

    // ---- Reset ----

    public void reset() {
        progressRepository.deleteAll();
    }

    // ---- Privé ----

    private UserProgressDTO.CategoryStats buildCategoryStats(QuestionCategory category) {
        long total = questionRepository.findByCategory(category).size();
        List<UserProgress> progresses = progressRepository.findByCategory(category);

        Map<ProgressStatus, Long> counts = progresses.stream()
                .collect(Collectors.groupingBy(UserProgress::getStatus, Collectors.counting()));

        long known = counts.getOrDefault(ProgressStatus.KNOWN, 0L);
        long unknown = counts.getOrDefault(ProgressStatus.UNKNOWN, 0L);
        int percentage = total > 0 ? (int) ((known * 100) / total) : 0;

        return UserProgressDTO.CategoryStats.builder()
                .category(category)
                .total(total)
                .known(known)
                .unknown(unknown)
                .percentage(percentage)
                .build();
    }
}