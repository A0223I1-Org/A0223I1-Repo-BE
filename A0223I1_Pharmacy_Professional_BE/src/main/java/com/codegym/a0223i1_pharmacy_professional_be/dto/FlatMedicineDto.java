package com.codegym.a0223i1_pharmacy_professional_be.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine}
 */
@Value
public class FlatMedicineDto  {
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
    Integer medicineImgId;
    String imgName;
    String link;
}