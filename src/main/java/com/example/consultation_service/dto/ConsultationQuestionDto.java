package com.example.consultation_service.dto;

import com.example.consultation_service.model.Question;

public class ConsultationQuestionDto {
  public Long questionId;
  public String questionText;
  public Question.QuestionType questionType;
  public String questionResponse;

  public ConsultationQuestionDto(Long questionId, String questionText,
                                 Question.QuestionType questionType) {
    this.questionId = questionId;
    this.questionText = questionText;
    this.questionType = questionType;
    this.questionResponse = null;
  }
}
