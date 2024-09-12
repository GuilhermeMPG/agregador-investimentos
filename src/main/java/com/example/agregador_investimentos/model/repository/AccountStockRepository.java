package com.example.agregador_investimentos.model.repository;

import com.example.agregador_investimentos.model.entity.AccountStock;
import com.example.agregador_investimentos.model.entity.AccountStockId;
import com.example.agregador_investimentos.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
