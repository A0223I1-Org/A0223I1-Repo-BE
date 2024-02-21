package com.codegym.a0223i1_pharmacy_professional_be.controller.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.JwtAuthenticationResponse;
import com.codegym.a0223i1_pharmacy_professional_be.dto.SignInRequest;
import com.codegym.a0223i1_pharmacy_professional_be.dto.SignUpRequest;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Account;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Account> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signinRequest){
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }
}
