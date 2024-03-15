package com.codegym.a0223i1_pharmacy_professional_be.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicinListF {

    private String medicineIdF;
    private String medicineNameF;
    private String unitF;
    private int quantityF;
    private Float retailPriceF;
}
