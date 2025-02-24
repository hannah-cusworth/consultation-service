package com.example.consultation_service.service;

import com.example.consultation_service.model.ConsultationType;
import com.example.consultation_service.model.Question;
import com.example.consultation_service.repository.ConsultationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("consultationTypeService")
public class ConsultationTypeService {
  private ConsultationTypeRepository repository;

  @Autowired
  public ConsultationTypeService(ConsultationTypeRepository consultationTypeRepository) {
    this.repository = consultationTypeRepository;
  }


  public List<ConsultationType> getAllConsultationTypes() {
    return repository.getAllConsultationTypes();
  }

  public List<Question> getAllQuestionsByConsultationTypeId(Long id) {
    ConsultationType type = repository.getConsultationTypeById(id);
    return type.getQuestions();
  }


}
