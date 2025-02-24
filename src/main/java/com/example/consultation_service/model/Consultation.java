package com.example.consultation_service.model;


// Table 'consultations'
public class Consultation {


    public enum ConsultationStatus {
        CREATED,
        REJECTED,
        AWAITING_REVIEW,
        PRESCRIBED,
    };

    private Long id; // PK

    private ConsultationType consultationType; // FK

    private ConsultationStatus status;


    public Consultation(ConsultationType type) {
        this.id = null;
        this.consultationType = type;
        this.status = ConsultationStatus.CREATED;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
      return id;
    }

    public ConsultationType getConsultationType() {
        return consultationType;
    }

    public ConsultationStatus getStatus() {
        return status;
    }

}
