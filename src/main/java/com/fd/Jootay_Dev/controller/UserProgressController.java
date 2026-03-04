package com.fd.Jootay_Dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fd.Jootay_Dev.dto.UserProgressDTO;
import com.fd.Jootay_Dev.enums.ProgressStatus;
import com.fd.Jootay_Dev.service.UserProgressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserProgressController {

    private final UserProgressService progressService;

    // Enregistrer le statut d'une question
    @PostMapping("/{questionId}")
    public ResponseEntity<UserProgressDTO.GlobalStats> setStatus(
            @PathVariable Long questionId,
            @RequestParam ProgressStatus status) {
        return ResponseEntity.ok(progressService.setStatus(questionId, status));
    }

    // Récupérer les stats globales
    @GetMapping("/stats")
    public ResponseEntity<UserProgressDTO.GlobalStats> getStats() {
        return ResponseEntity.ok(progressService.getStats());
    }

    // Réinitialiser toute la progression
    @DeleteMapping("/reset")
    public ResponseEntity<Void> reset() {
        progressService.reset();
        return ResponseEntity.noContent().build();
    }
}