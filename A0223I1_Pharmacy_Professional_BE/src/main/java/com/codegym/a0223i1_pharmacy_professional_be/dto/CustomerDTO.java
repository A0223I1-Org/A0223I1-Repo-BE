package com.codegym.a0223i1_pharmacy_professional_be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String customerId;

    private String customerName;

    private Integer age;

    private String address;

    private String phoneNumber;

    private String customerType;

    private String note;
    private Integer accountId;
}
