package com.codegym.a0223i1_pharmacy_professional_be.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierDTO {
    private String supplierId;
    private String supplierName;
    private String address;
    private String email;
    private String phoneNumber;
    private String note;

    private int toPayDebt;
    private Boolean deleteFlag;
}
