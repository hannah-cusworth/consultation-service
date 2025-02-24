package com.example.consultation_service.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// table 'questions'
public class Question {

  public static Question q1 = new Question(10L, "Do you have any preexitsting medical conditions?", Question.QuestionType.STRING);
  public static Question q2 = new Question(11L, "Are you allergic to any medications?", Question.QuestionType.STRING);
  public static Question q3 = new Question(12L, "Please describe your symptoms.", Question.QuestionType.STRING);
  public static List<Question> dummyList = Arrays.asList(q1, q2, q3);

  public Question(Long id, String text,
                  QuestionType questionType) {
    this.id = id;
    this.text = text;
    this.questionType = questionType;
  }

  public enum QuestionType {
    STRING,
//    BOOLEAN,
//    MULTIPLE_CHOICE
  }

  private Long id; // PK

  private String text;

  private QuestionType questionType;

  public Long getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public QuestionType getQuestionType() {
    return questionType;
  }
}
