package com.example.consultation_service.repository;

import com.example.consultation_service.model.Question;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static com.example.consultation_service.model.Question.dummyList;

@Component("questionRepository")
public class QuestionRepository {
  private HashMap<Long, Question> repository;

  public QuestionRepository() {

    this.repository = new HashMap<Long, Question>();
    for (Question question : dummyList) {
     this.repository.put(question.getId(), question);
    }
  }

  public Question getQuestionById(Long id) {
    return repository.get(id);
  }

  public void setDummyData() {

  }
}
