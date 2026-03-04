package com.fd.Jootay_Dev.model;

import com.fd.Jootay_Dev.enums.Difficulty;
import com.fd.Jootay_Dev.enums.QuestionCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 500)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;
    @Column(columnDefinition = "TEXT")
    private String codeExample;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionCategory category;

}
