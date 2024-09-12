package com.example.agregador_investimentos.model.repository;

import com.example.agregador_investimentos.model.entity.Account;
import com.example.agregador_investimentos.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
