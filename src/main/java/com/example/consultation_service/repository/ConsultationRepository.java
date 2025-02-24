package com.example.consultation_service.repository;

import com.example.consultation_service.exceptions.RepositoryException;
import com.example.consultation_service.model.Consultation;
import com.example.consultation_service.model.ConsultationType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component("consultationRepository")
public class ConsultationRepository  {

  // crude uuid generation, this would be done in sql
  private Long uuid = 0L;
  private HashMap<Long, Consultation> repository;

  public ConsultationRepository() {
    this.repository = new HashMap<Long, Consultation>();
  }

  public Consultation createConsultation(ConsultationType type) throws RepositoryException {
    Consultation consultation = new Consultation(type);
    try {
      consultation.setId(uuid++);
      this.repository.put(consultation.getId(), consultation);
      return consultation;
      // catch repository exception
    } catch (Exception ex) {
      // rollback creation
      this.repository.remove(consultation.getId());
      throw new RepositoryException("Failed to create consultation of type " + type.getId());
    }
  }

  public Consultation getConsultationById(Long id) {
   return this.repository.get(id);
  }

  public List<Consultation> getAllConsultations() {
    return new ArrayList<Consultation>(repository.values());
  }

}
