package com.rubenskj.security.loginlocation.model;

import javax.persistence.Entity;

@Entity
public class Client extends User {

    private String completeName;

    public Client() {
    }

    public Client(String email, String password, String completeName) {
        super(email, password);
        this.completeName = completeName;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }
}
