package com.example.consultation_service.controller;

import com.example.consultation_service.dto.ConsultationQuestionDto;
import com.example.consultation_service.dto.ConsultationTypeDto;
import com.example.consultation_service.model.Question;
import com.example.consultation_service.service.ConsultationTypeService;
import com.example.consultation_service.transformer.ConsultationTypeTransformer;
import com.example.consultation_service.transformer.QuestionTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

  @RestController
  @RequestMapping("/api/consultationtypes")
  public class ConsultationTypeController {

    @Autowired
    private ConsultationTypeService consultationTypeService;

    @GetMapping
    public ResponseEntity<List<ConsultationTypeDto>> getAllConsultationTypes() {
        List<ConsultationTypeDto> types = ConsultationTypeTransformer.toDto(consultationTypeService.getAllConsultationTypes());
        return ResponseEntity.status(HttpStatus.OK).body(types);
    }

  }

