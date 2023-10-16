package org.fdryt.ornamental.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.fdryt.ornamental.domain.user.User;
import org.fdryt.ornamental.service.JwtService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private static final long EXPIRATION_MINUTES = 30;
    private static final String SECRET_KEY = "bWFyaWExNw==";

    @Override
    public String generateToken(User user, Map<String, Object> extraClaims) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));

        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .header().add(Map.of("typ", "JWT"))
                .and()
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .decryptWith((SecretKey) generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    private Key generateKey() {
        byte[] decoded = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decoded);
    }
}
