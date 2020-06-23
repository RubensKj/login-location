package com.rubenskj.security.loginlocation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class Session {

    @Id
    private String id;
    private String uuid;
    private String email;
    private SessionDetails details;
    private Location location;
    private LocalDateTime dateLoggedIn;

    public Session() {
    }

    public Session(String email, SessionDetails details, Location location) {
        this.uuid = UUID.randomUUID().toString();
        this.email = email;
        this.details = details;
        this.location = location;
        this.dateLoggedIn = LocalDateTime.now();
    }

    public String getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public SessionDetails getDetails() {
        return details;
    }

    public void setDetails(SessionDetails details) {
        this.details = details;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getDateLoggedIn() {
        return dateLoggedIn;
    }

    public void setDateLoggedIn(LocalDateTime dateLoggedIn) {
        this.dateLoggedIn = dateLoggedIn;
    }
}
