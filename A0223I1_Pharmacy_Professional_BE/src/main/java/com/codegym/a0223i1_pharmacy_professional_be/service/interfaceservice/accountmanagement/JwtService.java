package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Objects;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    String generateRefreshToken(Map<String, Objects> extractClaims , UserDetails userDetails);
    String extractUserName(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
