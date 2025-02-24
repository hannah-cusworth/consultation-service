package com.example.consultation_service.service;

import com.example.consultation_service.exceptions.RepositoryException;
import com.example.consultation_service.exceptions.ServiceException;
import com.example.consultation_service.model.Answer;
import com.example.consultation_service.model.Consultation;
import com.example.consultation_service.model.Question;
import com.example.consultation_service.repository.AnswerRepository;
import com.example.consultation_service.repository.ConsultationRepository;
import com.example.consultation_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("questionService")
public class QuestionService {

  private QuestionRepository questionRepository;
  private AnswerRepository answerRepository;
  private ConsultationRepository consultationRepository;

  @Autowired
  public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
  }

  public Answer saveResponse(Answer answer, Long questionId, Long consultationId) throws ServiceException, RepositoryException {
    Question question = questionRepository.getQuestionById(questionId);
    if (question == null) throw new ServiceException(
        "Failed to save question response. Question with ID " + questionId.longValue() + " does not exist.");
    answer.setQuestion(question);
    Consultation consultation = consultationRepository.getConsultationById(consultationId);
    if (consultation == null) throw new ServiceException(
    "Failed to save question response. Consultation with ID " + questionId.longValue() + " does not exist.");
    answer.setConsultation(consultation);
    return answerRepository.updateOrCreateAnswer(answer);
  }

}
