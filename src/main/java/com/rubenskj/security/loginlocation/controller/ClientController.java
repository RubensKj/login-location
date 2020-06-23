package com.rubenskj.security.loginlocation.controller;

import com.rubenskj.security.loginlocation.dtos.ClientDTO;
import com.rubenskj.security.loginlocation.service.ClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientDTO saveClient(@Valid @RequestBody ClientDTO clientDTO) {
        return ClientDTO.of(this.clientService.saveClient(clientDTO));
    }
}
