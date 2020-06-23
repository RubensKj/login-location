package com.rubenskj.security.loginlocation.service;

import com.rubenskj.security.loginlocation.dtos.ClientDTO;
import com.rubenskj.security.loginlocation.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.rubenskj.security.loginlocation.util.ValidationUtils.validateString;

@Service
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public ClientService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public Client saveClient(ClientDTO clientDTO) {
        this.validateClient(clientDTO);

        LOGGER.info("Saving client");

        Client client = new Client(clientDTO.getEmail(), passwordEncoder.encode(clientDTO.getPassword()), clientDTO.getCompleteName());

        return (Client) userService.save(client);
    }

    private void validateClient(ClientDTO clientDTO) {
        validateString(clientDTO.getCompleteName(), "Complete name cannot be null.");
    }
}
