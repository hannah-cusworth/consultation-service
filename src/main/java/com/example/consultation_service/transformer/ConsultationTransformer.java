package com.example.consultation_service.transformer;

import com.example.consultation_service.dto.ConsultationDto;
import com.example.consultation_service.dto.ConsultationQuestionDto;
import com.example.consultation_service.dto.ConsultationTypeDto;
import com.example.consultation_service.exceptions.TransformationException;
import com.example.consultation_service.model.Consultation;
import com.example.consultation_service.model.ConsultationType;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultationTransformer {

  public static List<ConsultationDto> toDto(List<Consultation> consultations) {
    return consultations.stream().map(ConsultationTransformer::toDtoElement).collect(Collectors.toList());
  }

  public static ConsultationDto toDtoElement(Consultation consultation) {
    ConsultationType type = consultation.getConsultationType();
    ConsultationTypeDto typeDto = new ConsultationTypeDto(type.getId(), type.getName());
    List<ConsultationQuestionDto> questions = QuestionTransformer.toDto(type.getQuestions());
    return new ConsultationDto(consultation.getId(), typeDto, questions, consultation.getStatus());
  }

  public static Consultation fromDtoElement(ConsultationDto consultationDto) throws TransformationException {
    if (consultationDto.consultationQuestions != null || consultationDto.id != null|| consultationDto.status != null)
      throw new TransformationException("Invalid data: consultation must contain only a consultation type");
    ConsultationType type = ConsultationTypeTransformer.fromDtoElement(consultationDto.consultationTypeDto);
    return new Consultation(type);
  }

}
