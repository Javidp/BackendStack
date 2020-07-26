package com.jd.backend.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfiguration {

    @Value("${auth.jwt.token.key.path}")
    private String jwtTokenKeyPath;

    @Value("${auth.jwt.token.key.password}")
    private String jwtTokenKeyPassword;

    @Value("${auth.jwt.token.key.alias}")
    private String jwtTokenKeyAlias;

    public String getJwtTokenKeyPath() {
        return jwtTokenKeyPath;
    }

    public char[] getJwtTokenKeyPassword() {
        return jwtTokenKeyPassword.toCharArray();
    }

    public String getJwtTokenKeyAlias() {
        return jwtTokenKeyAlias;
    }

}
