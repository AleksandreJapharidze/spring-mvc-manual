package com.manualspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;

@Configuration 
public class JwtLibConfig {
    @Bean
    public JWSSigner jwsSigner() throws KeyLengthException {
        return new MACSigner("super-secret-string-that-is-long-enough-for-jwt");
    }
}
