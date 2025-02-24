package com.example.consultation_service.repository;


import com.example.consultation_service.exceptions.RepositoryException;
import com.example.consultation_service.model.Answer;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component("answerRepository")
public class AnswerRepository {
    private HashMap<Long, Answer> repository;

    public AnswerRepository() {
      this.repository = new HashMap<Long, Answer>();
    }

    public Answer updateOrCreateAnswer(Answer answer) throws RepositoryException {
      try {
          // simulate PK generation
          answer.setId();
          this.repository.put(answer.getId(), answer);
          return answer;
          // catch repository exception
        } catch(Exception ex){
          // rollback creation
          this.repository.remove(answer.getId());
          throw new RepositoryException("Failed to add answer " + answer + " to repository");
        }
      }
}


