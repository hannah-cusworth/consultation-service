package com.example.consultation_service.dto;

import com.example.consultation_service.model.Consultation;

import java.util.List;

public class ConsultationDto {
  public Long id;
  public ConsultationTypeDto consultationTypeDto;
  public List<ConsultationQuestionDto> consultationQuestions;
  public Consultation.ConsultationStatus status;

  public ConsultationDto(Long id,
                         ConsultationTypeDto consultationType,
                         List<ConsultationQuestionDto> consultationQuestions,
                         Consultation.ConsultationStatus consultationStatus) {
    this.id = id;
    this.consultationTypeDto = consultationType;
    this.consultationQuestions = consultationQuestions;
    this.status = consultationStatus;
  }

}
