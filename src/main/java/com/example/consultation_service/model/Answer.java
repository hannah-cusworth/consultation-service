package com.example.consultation_service.model;


// Table 'answers'
public class Answer {

  private Long id;
  private Consultation consultation; // FK => composite PK
  private Question question; // FK => composite PK
  private String answer;


  public Answer(String answer) {
    this.answer = answer;
    this.question = null;
    this.consultation = null;
    this.id = null;
  }

  public Long getId() {
    return id;
  }

  // this is obviously broken but needed simple deterministic simulation of composite PK
  public void setId() {
    this.id = consultation.getId() + question.getId();
  }

  public Question getQuestion() {
    return question;
  }
  public Consultation getConsultation() {
    return consultation;
  }

  public String getAnswer() {
    return answer;
  }

  public void setConsultation(Consultation consultation) {
    this.consultation = consultation;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }
}
