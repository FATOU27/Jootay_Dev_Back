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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Progression", description = "Suivi de la progression par question")
public class UserProgressController {

    private final UserProgressService progressService;

    @PostMapping("/{questionId}")
    @Operation(summary = "Enregistrer le statut d'une question", description = "Marque une question comme KNOWN ou UNKNOWN et retourne les stats globales mises à jour")
    @ApiResponse(responseCode = "200", description = "Statut enregistré, stats retournées")
    public ResponseEntity<UserProgressDTO.GlobalStats> setStatus(
            @Parameter(description = "ID de la question") @PathVariable Long questionId,
            @Parameter(description = "Statut : KNOWN ou UNKNOWN") @RequestParam ProgressStatus status) {
        return ResponseEntity.ok(progressService.setStatus(questionId, status));
    }

    @GetMapping("/stats")
    @Operation(summary = "Statistiques globales", description = "Retourne le nombre de questions maîtrisées, à revoir, et non touchées, par catégorie")
    @ApiResponse(responseCode = "200", description = "Stats retournées")
    public ResponseEntity<UserProgressDTO.GlobalStats> getStats() {
        return ResponseEntity.ok(progressService.getStats());
    }

    @DeleteMapping("/reset")
    @Operation(summary = "Réinitialiser la progression", description = "Supprime toute la progression enregistrée")
    @ApiResponse(responseCode = "204", description = "Progression réinitialisée")
    public ResponseEntity<Void> reset() {
        progressService.reset();
        return ResponseEntity.noContent().build();
    }
}