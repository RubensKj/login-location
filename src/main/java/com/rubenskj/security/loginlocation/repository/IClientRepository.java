package com.rubenskj.security.loginlocation.repository;

import com.rubenskj.security.loginlocation.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
}
