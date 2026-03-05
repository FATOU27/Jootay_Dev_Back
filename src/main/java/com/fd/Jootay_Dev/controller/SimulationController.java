package com.fd.Jootay_Dev.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fd.Jootay_Dev.dto.QuestionDTO;
import com.fd.Jootay_Dev.dto.SimulationDTO;
import com.fd.Jootay_Dev.enums.QuestionCategory;
import com.fd.Jootay_Dev.service.SimulationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/simulation")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Simulation", description = "Mode simulation d'entretien")
public class SimulationController {

    private final SimulationService simulationService;

    @GetMapping("/questions")
    @Operation(summary = "Questions aléatoires pour une session", description = "Retourne N questions mélangées aléatoirement, filtrables par catégorie")
    @ApiResponse(responseCode = "200", description = "Liste de questions aléatoires")
    public ResponseEntity<List<QuestionDTO>> getRandomQuestions(
            @Parameter(description = "Catégorie (optionnel)") @RequestParam(required = false) QuestionCategory category,
            @Parameter(description = "Nombre de questions (défaut: 10)") @RequestParam(defaultValue = "10") int count) {
        return ResponseEntity.ok(simulationService.getRandomQuestions(category, count));
    }

    @PostMapping("/session")
    @Operation(summary = "Sauvegarder une session", description = "Enregistre le résultat d'une session et met à jour la progression des questions ratées")
    @ApiResponse(responseCode = "200", description = "Session sauvegardée avec résumé")
    public ResponseEntity<SimulationDTO.SessionResponse> saveSession(
            @RequestBody SimulationDTO.SaveSessionRequest request) {
        return ResponseEntity.ok(simulationService.saveSession(request));
    }

    @GetMapping("/history")
    @Operation(summary = "Historique des sessions", description = "Retourne toutes les sessions triées par date décroissante")
    @ApiResponse(responseCode = "200", description = "Historique retourné")
    public ResponseEntity<List<SimulationDTO.SessionResponse>> getHistory() {
        return ResponseEntity.ok(simulationService.getHistory());
    }
}