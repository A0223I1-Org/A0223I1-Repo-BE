package com.codegym.a0223i1_pharmacy_professional_be.service.implement.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.JwtAuthenticationResponse;
import com.codegym.a0223i1_pharmacy_professional_be.dto.SignInRequest;
import com.codegym.a0223i1_pharmacy_professional_be.dto.SignUpRequest;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Role;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Account;
import com.codegym.a0223i1_pharmacy_professional_be.repository.accountmanagement.UserRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement.AuthenticationService;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public Account signup(SignUpRequest signUpRequest){
        Account account = new Account();
        account.setEmail(signUpRequest.getEmail());
        account.setFirst_name(signUpRequest.getFirstname());
        account.setLast_name(signUpRequest.getLastname());
        account.setRole(Role.URER);
        account.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(account);
    }
    public JwtAuthenticationResponse signin(SignInRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
        var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

}
