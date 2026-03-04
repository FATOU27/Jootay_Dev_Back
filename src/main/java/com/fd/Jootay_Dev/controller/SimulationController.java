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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/simulation")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class SimulationController {

    private final SimulationService simulationService;

    // Récupérer N questions aléatoires pour démarrer une session
    @GetMapping("/questions")
    public ResponseEntity<List<QuestionDTO>> getRandomQuestions(
            @RequestParam(required = false) QuestionCategory category,
            @RequestParam(defaultValue = "10") int count) {
        return ResponseEntity.ok(simulationService.getRandomQuestions(category, count));
    }

    // Sauvegarder une session terminée
    @PostMapping("/session")
    public ResponseEntity<SimulationDTO.SessionResponse> saveSession(
            @RequestBody SimulationDTO.SaveSessionRequest request) {
        return ResponseEntity.ok(simulationService.saveSession(request));
    }

    // Historique de toutes les sessions
    @GetMapping("/history")
    public ResponseEntity<List<SimulationDTO.SessionResponse>> getHistory() {
        return ResponseEntity.ok(simulationService.getHistory());
    }
}