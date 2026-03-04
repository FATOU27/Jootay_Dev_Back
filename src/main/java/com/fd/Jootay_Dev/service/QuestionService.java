package com.fd.Jootay_Dev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fd.Jootay_Dev.dto.QuestionDTO;
import com.fd.Jootay_Dev.enums.Difficulty;
import com.fd.Jootay_Dev.enums.QuestionCategory;
import com.fd.Jootay_Dev.model.Question;
import com.fd.Jootay_Dev.reporitory.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public QuestionDTO getQuestionById(Long id) {
        return questionRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Question non trouvée : " + id));
    }

    public List<QuestionDTO> getQuestionsByFilters(QuestionCategory category, Difficulty difficulty) {
        List<Question> questions;

        if (category != null && difficulty != null) {
            questions = questionRepository.findByCategoryAndDifficulty(category, difficulty);
        } else if (category != null) {
            questions = questionRepository.findByCategory(category);
        } else if (difficulty != null) {
            questions = questionRepository.findByDifficulty(difficulty);
        } else {
            questions = questionRepository.findAll();
        }

        return questions.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public QuestionDTO createQuestion(QuestionDTO dto) {
        Question question = toEntity(dto);
        return toDTO(questionRepository.save(question));
    }

    // ---- Mappers ----

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

    private Question toEntity(QuestionDTO dto) {
        return Question.builder()
                .title(dto.getTitle())
                .answer(dto.getAnswer())
                .codeExample(dto.getCodeExample())
                .difficulty(dto.getDifficulty())
                .category(dto.getCategory())
                .build();
    }
}