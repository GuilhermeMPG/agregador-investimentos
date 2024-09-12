package com.example.agregador_investimentos.model.service;

import com.example.agregador_investimentos.dto.CreateStockDtoRequest;
import com.example.agregador_investimentos.model.entity.Stock;
import com.example.agregador_investimentos.model.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public void creatStock(CreateStockDtoRequest createStockDtoRequest) {

        Stock stock = new Stock(
                createStockDtoRequest.description(),
                createStockDtoRequest.stockId()
        );

        stockRepository.save(stock);
    }
}
