package com.example.consultation_service.transformer;

import com.example.consultation_service.dto.ConsultationQuestionDto;
import com.example.consultation_service.exceptions.TransformationException;
import com.example.consultation_service.model.Answer;
import com.example.consultation_service.model.Question;


public class AnswerTransformer {

  public static Answer fromDtoElement(ConsultationQuestionDto question) throws
      TransformationException {
    AnswerTransformer.validateData(question.questionType, question.questionResponse);
    return new Answer(question.questionResponse);
  }

  // This obviously needs expanding, ran out of time.
  // See readme for planned implementation of handling different response data types.
  private static boolean validateData(Question.QuestionType dataType, String questionResponse) throws TransformationException {
    if (questionResponse == null)
      throw new TransformationException("Invalid Data: question response cannot be null");
    return true;
  }

}


