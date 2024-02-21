package com.codegym.a0223i1_pharmacy_professional_be.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
