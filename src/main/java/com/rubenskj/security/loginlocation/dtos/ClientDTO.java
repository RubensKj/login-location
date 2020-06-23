package com.rubenskj.security.loginlocation.dtos;

import com.rubenskj.security.loginlocation.model.Client;

public class ClientDTO extends UserDTO {
    private String completeName;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String email, String completeName) {
        super(id, email);
        this.completeName = completeName;
    }

    public static ClientDTO of(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getEmail(),
                client.getCompleteName()
        );
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }
}
