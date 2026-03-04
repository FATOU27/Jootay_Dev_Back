package com.fd.Jootay_Dev.dto;

import com.fd.Jootay_Dev.enums.Difficulty;
import com.fd.Jootay_Dev.enums.QuestionCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {
    private Long id;
    private String title;
    private String answer;
    private String codeExample;
    private Difficulty difficulty;
    private QuestionCategory category;

}
