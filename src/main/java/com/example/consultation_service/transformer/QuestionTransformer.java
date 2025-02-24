package com.example.consultation_service.transformer;

import com.example.consultation_service.dto.ConsultationQuestionDto;
import com.example.consultation_service.model.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionTransformer {
  public static List<ConsultationQuestionDto> toDto(List<Question> questions) {
   return questions.stream().map(QuestionTransformer::toDtoElement).collect(Collectors.toList());
  }

  public static ConsultationQuestionDto toDtoElement(Question question) {
    return new ConsultationQuestionDto(question.getId(), question.getText(), question.getQuestionType());
  }
}
