package com.rubenskj.security.loginlocation.service;

import com.rubenskj.security.loginlocation.dtos.AuthDTO;
import com.rubenskj.security.loginlocation.dtos.LoginDTO;
import com.rubenskj.security.loginlocation.security.auth.UuidProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.rubenskj.security.loginlocation.util.ValidationUtils.validateString;

@Service
public class AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final UuidProvider uuidProvider;

    public AuthService(AuthenticationManager authenticationManager, UuidProvider uuidProvider) {
        this.authenticationManager = authenticationManager;
        this.uuidProvider = uuidProvider;
    }

    public AuthDTO authenticate(LoginDTO loginDTO, HttpServletRequest request) {
        this.validateLogin(loginDTO);

        LOGGER.info("Authenticating user.");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        LOGGER.info("User authenticated");

        return this.uuidProvider.generateSessionToUserAuth(authentication, request);
    }

    private void validateLogin(LoginDTO loginDTO) {
        validateString(loginDTO.getEmail(), "E-mail cannot be null");
        validateString(loginDTO.getPassword(), "Password cannot be null");
    }
}
