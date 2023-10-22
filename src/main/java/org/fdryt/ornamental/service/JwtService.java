package org.fdryt.ornamental.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {

    String generateToken(UserDetails userDetails);
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
