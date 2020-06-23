package com.rubenskj.security.loginlocation.dtos;

import java.time.LocalDateTime;

public class AuthDTO {

    private String token;
    private LocalDateTime dateLoggedIn;

    public AuthDTO() {
    }

    public AuthDTO(String token, LocalDateTime dateLoggedIn) {
        this.token = token;
        this.dateLoggedIn = dateLoggedIn;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getDateLoggedIn() {
        return dateLoggedIn;
    }
}
