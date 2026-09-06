package com.manualspring.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Component
public class JwtUtil {
    private static final long EXPIRATION_TIME = 1000 * 60 * 20;

    private final JWSSigner jwsSigner;

    public JwtUtil(JWSSigner jwsSigner) {
        this.jwsSigner = jwsSigner;
    }

    public String generateToken(String username) {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
            .subject(username)
            .issueTime(new Date())
            .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

        try {
            signedJWT.sign(jwsSigner);
        } catch (JOSEException e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not sign JWT");
        }

        return signedJWT.serialize();
    }
}
