package com.codegym.a0223i1_pharmacy_professional_be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDtoCreateUpdate {
    private String employee_id;
    private String employee_name;
    private String phone_number;
    private Date date_start;
    private String address;
    private String note;
    private int salary;
    private String image;
    private Integer account_id;
    private String email;
    private String password;
    private Long role_id;
}
