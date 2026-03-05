package com.fd.Jootay_Dev.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fd.Jootay_Dev.dto.QuestionDTO;
import com.fd.Jootay_Dev.enums.Difficulty;
import com.fd.Jootay_Dev.enums.QuestionCategory;
import com.fd.Jootay_Dev.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Questions", description = "Gestion des questions Java")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    @Operation(summary = "Lister les questions", description = "Retourne toutes les questions avec filtres optionnels par catégorie et difficulté")
    @ApiResponse(responseCode = "200", description = "Liste des questions")
    public ResponseEntity<List<QuestionDTO>> getAll(
            @Parameter(description = "Filtrer par catégorie") @RequestParam(required = false) QuestionCategory category,
            @Parameter(description = "Filtrer par difficulté") @RequestParam(required = false) Difficulty difficulty) {
        return ResponseEntity.ok(questionService.getQuestionsByFilters(category, difficulty));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une question par ID")
    @ApiResponse(responseCode = "200", description = "Question trouvée")
    @ApiResponse(responseCode = "404", description = "Question non trouvée")
    public ResponseEntity<QuestionDTO> getById(
            @Parameter(description = "ID de la question") @PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle question")
    @ApiResponse(responseCode = "200", description = "Question créée")
    public ResponseEntity<QuestionDTO> create(@RequestBody QuestionDTO dto) {
        return ResponseEntity.ok(questionService.createQuestion(dto));
    }
}