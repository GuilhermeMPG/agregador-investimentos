package com.example.agregador_investimentos.model.service;

import com.example.agregador_investimentos.dto.AccountStockDtoResponse;
import com.example.agregador_investimentos.dto.AssociateAccountStockDtoRequest;
import com.example.agregador_investimentos.dto.CreateAccountDtoRequest;
import com.example.agregador_investimentos.dto.CreateStockDtoRequest;
import com.example.agregador_investimentos.model.entity.*;
import com.example.agregador_investimentos.model.repository.AccountRepository;
import com.example.agregador_investimentos.model.repository.AccountStockRepository;
import com.example.agregador_investimentos.model.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private AccountStockRepository accountStockRepository;


    public void associateStock(String accountId, AssociateAccountStockDtoRequest associateAccountStockDtoRequest) {

        Account account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Stock stock = stockRepository.findById(associateAccountStockDtoRequest.stockId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var id = new AccountStockId(account.getAccountId(),stock.getStockId());
        AccountStock entity = new AccountStock(
                id,
                account,
                stock,
                associateAccountStockDtoRequest.quantity()

        );
        accountStockRepository.save(entity);
    }

    public List<AccountStockDtoResponse> listStocks(String accountId) {
        Account account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return account.getAccountStocks()
                .stream()
                .map(as -> new AccountStockDtoResponse(as.getStock().toString(),as.getQuantity(), 0.0)).toList();


    }
}
