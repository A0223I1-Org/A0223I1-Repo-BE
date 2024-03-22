package com.codegym.a0223i1_pharmacy_professional_be.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
    private String dateCreateF;
    private String noteF;
    private String employeeIdF;
    private String customerIdF;
    private String symptomIdF;
    private Float totalF;
    private List<MedicinListF> medicineListF;

}
