package com.rubenskj.security.loginlocation.controller;

import com.rubenskj.security.loginlocation.dtos.AuthDTO;
import com.rubenskj.security.loginlocation.dtos.LoginDTO;
import com.rubenskj.security.loginlocation.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthDTO login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        return this.authService.authenticate(loginDTO, request);
    }
}
