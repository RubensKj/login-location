package com.rubenskj.security.loginlocation.service;

import com.rubenskj.security.loginlocation.model.User;
import com.rubenskj.security.loginlocation.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.rubenskj.security.loginlocation.util.ValidationUtils.validateString;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        this.validateUserDto(user);

        LOGGER.info("Saving User");
        LOGGER.debug("User: {}", user);

        return this.userRepository.save(user);
    }

    private void validateUserDto(User userDTO) {
        validateString(userDTO.getEmail(), "E-mail cannot be null");
        validateString(userDTO.getPassword(), "Password cannot be null");
    }
}
