package com.example.consultation_service.service;

import com.example.consultation_service.dto.ConsultationDto;
import com.example.consultation_service.exceptions.RepositoryException;
import com.example.consultation_service.exceptions.ServiceException;
import com.example.consultation_service.model.Consultation;
import com.example.consultation_service.model.ConsultationType;
import com.example.consultation_service.repository.ConsultationRepository;
import com.example.consultation_service.repository.ConsultationTypeRepository;
import com.example.consultation_service.transformer.ConsultationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("consultationService")
public class ConsultationService {
  private ConsultationRepository consultationRepository;
  private ConsultationTypeRepository typeRepository;

  @Autowired
  public ConsultationService(ConsultationRepository consultationRepository, ConsultationTypeRepository consultationTypeRepository) {
    this.consultationRepository = consultationRepository;
    this.typeRepository = consultationTypeRepository;

  }

  public Consultation createConsultationWithType(Long consultationTypeId) throws
      ServiceException, RepositoryException {
    ConsultationType type = typeRepository.getConsultationTypeById(consultationTypeId);
    if (type == null) throw new ServiceException(
        "Consultation creation failed. Consultation Type ID does not exist.");
     return consultationRepository.createConsultation(type);
  }

  public Consultation getConsultationById(Long id) {
    return consultationRepository.getConsultationById(id);
  }

  public List<Consultation> getAllConsultations() {
    return consultationRepository.getAllConsultations();
  }

}
