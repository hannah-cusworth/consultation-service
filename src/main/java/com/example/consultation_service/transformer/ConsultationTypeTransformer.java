package com.example.consultation_service.transformer;

import com.example.consultation_service.dto.ConsultationTypeDto;
import com.example.consultation_service.model.ConsultationType;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultationTypeTransformer {

  public static List<ConsultationTypeDto> toDto(List<ConsultationType> types) {
    return types.stream().map(ConsultationTypeTransformer::toDtoElement).collect(Collectors.toList());
  }

  public static ConsultationTypeDto toDtoElement(ConsultationType type) {
    return new ConsultationTypeDto(type.getId(), type.getName());
  }

  public static ConsultationType fromDtoElement(ConsultationTypeDto typeDto) {
    return new ConsultationType(typeDto.id, null, null);
  }
}
