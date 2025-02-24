package com.example.consultation_service.controller;

import com.example.consultation_service.dto.ConsultationDto;
import com.example.consultation_service.dto.ConsultationQuestionDto;
import com.example.consultation_service.exceptions.RepositoryException;
import com.example.consultation_service.exceptions.ServiceException;
import com.example.consultation_service.exceptions.TransformationException;
import com.example.consultation_service.model.Answer;
import com.example.consultation_service.model.Consultation;
import com.example.consultation_service.service.ConsultationService;
import com.example.consultation_service.service.QuestionService;
import com.example.consultation_service.transformer.AnswerTransformer;
import com.example.consultation_service.transformer.ConsultationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
  @RequestMapping("/api/consultations")
  public class ConsultationController {

  @Autowired
  private ConsultationService consultationService;
  @Autowired
  private QuestionService questionService;

  @GetMapping
  public ResponseEntity<Object> getConsultation() {
    List<ConsultationDto> consultationDtos = ConsultationTransformer.toDto(consultationService.getAllConsultations());
    return ResponseEntity.status(HttpStatus.OK).body(consultationDtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getConsultation(@PathVariable Long id ) {
    ConsultationDto consultationDto = ConsultationTransformer.toDtoElement(consultationService.getConsultationById(id));
    return ResponseEntity.status(HttpStatus.OK).body(consultationDto);
  }

  @PostMapping
  public ResponseEntity<Object> createConsultation(@RequestBody ConsultationDto consultationDto) {
    try {
      Consultation consultation = ConsultationTransformer.fromDtoElement(consultationDto);
      ConsultationDto result =
          ConsultationTransformer.toDtoElement(
              consultationService.createConsultationWithType(consultation.getConsultationType().getId()));
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } catch (TransformationException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Invalid data: consultation must contain only a consultation type");
    } catch (ServiceException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Invalid data: " + ex.getMessage());
    } catch (RepositoryException ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Server Error: " + ex.getMessage());
    }
  }


  @PutMapping("/{id}/question")
  public ResponseEntity<Object> putConsultationQuestion(
      @RequestBody ConsultationQuestionDto question, @PathVariable Long id) {
      try {
        Answer answer = AnswerTransformer.fromDtoElement(question);
        Answer result = questionService.saveResponse(answer, question.questionId, id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
      } catch (TransformationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Invalid data: consultation question invalid.");
      } catch (ServiceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Invalid data: " + ex.getMessage());
      } catch (RepositoryException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Server Error: " + ex.getMessage());
      }
  }
}
