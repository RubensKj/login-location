package com.rubenskj.security.loginlocation.service;

import com.rubenskj.security.loginlocation.model.Session;
import com.rubenskj.security.loginlocation.repository.ISessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.rubenskj.security.loginlocation.util.ValidationUtils.validateString;

@Service
public class SessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);

    private final ISessionRepository sessionRepository;

    public SessionService(ISessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session save(Session session) {
        this.validate(session);

        LOGGER.info("Saving session");
        LOGGER.debug("Session: {}", session);

        return this.sessionRepository.save(session);
    }

    private void validate(Session session) {
        validateString(session.getUuid(), "UUID Token cannot be null.");
        validateString(session.getEmail(), "E-mail of session cannot be null.");
    }
}
