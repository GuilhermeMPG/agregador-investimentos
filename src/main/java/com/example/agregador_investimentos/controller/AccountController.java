package com.example.agregador_investimentos.controller;

import com.example.agregador_investimentos.dto.AccountStockDtoResponse;
import com.example.agregador_investimentos.dto.AssociateAccountStockDtoRequest;
import com.example.agregador_investimentos.dto.CreateAccountDtoRequest;
import com.example.agregador_investimentos.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId") String accountId,
                                               @RequestBody AssociateAccountStockDtoRequest associateAccountStockDtoRequest) {
        accountService.associateStock(accountId, associateAccountStockDtoRequest);

        return ResponseEntity.ok().build();


    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockDtoResponse>> associateStockList(@PathVariable("accountId") String accountId) {
        return ResponseEntity.ok(accountService.listStocks(accountId));


    }


}
