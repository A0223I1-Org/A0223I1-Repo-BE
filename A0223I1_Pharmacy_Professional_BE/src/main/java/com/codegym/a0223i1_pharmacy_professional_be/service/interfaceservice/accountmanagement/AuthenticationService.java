package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.JwtAuthenticationResponse;
import com.codegym.a0223i1_pharmacy_professional_be.dto.SignInRequest;
import com.codegym.a0223i1_pharmacy_professional_be.dto.SignUpRequest;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Account;

public interface AuthenticationService {
    Account signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signinRequest);
}
