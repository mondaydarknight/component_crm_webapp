package com.smartbee.crm.core.services;

import com.smargbee.openapi_generated.crm.model.JWTToken;
import com.smartbee.crm.core.security.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    public AuthService(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public JWTToken authenticate(UsernamePasswordAuthenticationToken authenticationToken)
            throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JWTToken jwtToken = new JWTToken();
        jwtToken.setToken(tokenProvider.generateToken(authentication));
        return jwtToken;
    }
}
