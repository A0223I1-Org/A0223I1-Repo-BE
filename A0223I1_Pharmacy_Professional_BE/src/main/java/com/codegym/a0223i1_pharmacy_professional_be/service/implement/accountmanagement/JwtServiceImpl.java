package com.codegym.a0223i1_pharmacy_professional_be.service.implement.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService{
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSiginKey(), SignatureAlgorithm.ES256)
                .compact();
    }
    public String generateRefreshToken(Map<String, Objects> extractClaims , UserDetails userDetails){
        return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+604800000))
                .signWith(getSiginKey(), SignatureAlgorithm.ES256)
                .compact();
    }

    public  String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolves){
        final Claims claims = extractAllClaims(token);
        return claimsResolves.apply(claims);

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSiginKey(){
        byte[] key = Decoders.BASE64.decode("12313123123123123123EWR123123EQWE123123QEQE123ASDA1313");
        return Keys.hmacShaKeyFor(key);
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
