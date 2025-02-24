package com.example.consultation_service.repository;

import com.example.consultation_service.model.ConsultationType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.consultation_service.model.ConsultationType.genovianPear;


@Component("consultationTypeRepository")
public class ConsultationTypeRepository {

  private HashMap<Long, ConsultationType> repository;

  public ConsultationTypeRepository() {
    this.repository = new HashMap<Long, ConsultationType>();
    this.repository.put(genovianPear.getId(), genovianPear);
  }

  public List<ConsultationType> getAllConsultationTypes() {
    return new ArrayList<ConsultationType>(repository.values());
  }

  public ConsultationType getConsultationTypeById(Long id) {
    return repository.get(id);
  }

}
