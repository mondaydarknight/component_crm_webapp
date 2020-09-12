package com.smartbee.crm.controller;

import com.smargbee.openapi_generated.crm.model.ApiAuthentication;
import com.smargbee.openapi_generated.crm.model.JWTToken;
import com.smartbee.crm.core.security.jwt.JWTConfigurer;
import com.smartbee.crm.core.services.AuthService;
import com.smartbee.openapi_generated.crm.api.AuthenticateApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/crm/v1")
public class AuthController implements AuthenticateApi {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    private final HttpServletResponse response;

    public AuthController(AuthService authService, HttpServletResponse response) {
        this.authService = authService;
        this.response = response;
    }

    @Override
    public ResponseEntity authenticate(@Valid @RequestBody ApiAuthentication apiAuthentication) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                apiAuthentication.getLoginId(), apiAuthentication.getPassword()
        );

        try {
            JWTToken jwtToken = authService.authenticate(authenticationToken);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwtToken.getToken());
            return ResponseEntity.ok(jwtToken);
        } catch (AuthenticationException e) {
            LOG.trace("Authentication exception: {}", e);
            return new ResponseEntity<>(
                    Collections.singletonMap("AuthenticationException", e.getLocalizedMessage()),
                    HttpStatus.UNAUTHORIZED
            );
        }
    }
}
