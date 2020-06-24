package com.rubenskj.security.loginlocation.controller;

import com.rubenskj.security.loginlocation.dtos.ClientDTO;
import com.rubenskj.security.loginlocation.service.ClientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('RL_ADMIN')")
    public ClientDTO getClient(@PathVariable("id") Long id) {
        return ClientDTO.of(this.clientService.getClientById(id));
    }
}
