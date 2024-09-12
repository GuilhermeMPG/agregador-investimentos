package com.example.agregador_investimentos.controller;

import com.example.agregador_investimentos.dto.CreateStockDtoRequest;
import com.example.agregador_investimentos.dto.CreateUserDtoRequest;
import com.example.agregador_investimentos.model.entity.User;
import com.example.agregador_investimentos.model.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {
    @Autowired
    private StockService stockService;
    @PostMapping
    public ResponseEntity<Void> creatStock(@RequestBody CreateStockDtoRequest createStockDtoRequest) {
        stockService.creatStock(createStockDtoRequest);
        return ResponseEntity.ok().build();


    }

}
