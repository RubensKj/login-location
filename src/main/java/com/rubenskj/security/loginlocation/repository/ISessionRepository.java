package com.rubenskj.security.loginlocation.repository;

import com.rubenskj.security.loginlocation.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISessionRepository extends MongoRepository<Session, String> {
    Optional<Session> findByUuid(String uuid);
}
