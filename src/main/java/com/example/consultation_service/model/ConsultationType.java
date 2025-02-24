package com.example.consultation_service.model;

import java.util.List;

// Table 'consultation_type
public class ConsultationType {

  public static ConsultationType genovianPear = ConsultationType.createNewType(123L, "Genovian Pear allergy", Question.dummyList);

  private Long id; // PK

  private String name;

  private List<Question> questions; // ManyToMany Question FK <=> ConsultationType FK

  public ConsultationType(Long id, String name, List<Question> questions) {
    this.id = id;
    this.name = name;
    this.questions = questions;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  // utility for adding dummy data to repo
  public static ConsultationType createNewType(Long id, String name, List<Question> questions) {
   return new ConsultationType(id, name, questions);
  }

}
