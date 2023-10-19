package org.fdryt.ornamental.service;

import org.fdryt.ornamental.domain.user.User;

import java.util.Map;

public interface JwtService {

    String generateToken(User userObtained, Map<String, Object> stringObjectMap);

    String extractUsername(String jwt);
}
