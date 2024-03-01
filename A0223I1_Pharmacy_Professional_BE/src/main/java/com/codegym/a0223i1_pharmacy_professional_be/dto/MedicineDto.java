package com.codegym.a0223i1_pharmacy_professional_be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MedicineDto {
    String medicineId;
    String medicineName;
    String material;
    String activeIngredient;
    String unit;
    String conversionUnit;
    int quantity;
    Float importPrice;
    Float wholesalePrice;
    Float retailPrice;
    Float supplierDiscount;
    Float profitMarginWholesale;
    Float profitMarginRetail;
    String origin;
    String status;

    List<String> medicineImgLinkList;

    // Constructor to initialize the list
    public MedicineDto() {
        this.medicineImgLinkList = new ArrayList<>();
    }
}
