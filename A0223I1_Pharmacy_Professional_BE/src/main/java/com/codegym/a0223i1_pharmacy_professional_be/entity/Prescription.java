package com.codegym.a0223i1_pharmacy_professional_be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prescription")
public class Prescription {
    @Id
    @Column(name = "prescription_id")
    private String prescriptionId;

    private String prescriptionName;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "symptom_id", referencedColumnName = "symptom_id")
    @JsonBackReference
    private Symptom symptom;

    private String target;
    @Column(name = "treatment_period")
    private  String treatmentPeriod;
    private String note;

    private Boolean deleteFlag;

    @Transient
    private String symptomName;

    @OneToMany(mappedBy = "prescription")
    @JsonBackReference
    List<PrescriptionDetail> prescriptionDetails;

    @OneToMany(mappedBy = "prescription")
    @JsonBackReference
    List<Invoice> invoices;

}