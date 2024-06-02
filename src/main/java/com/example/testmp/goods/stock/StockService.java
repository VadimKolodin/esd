package com.example.testmp.goods.stock;

import com.example.testmp.goods.stock.data.StockRepository;
import com.example.testmp.user.seller.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public Stock getStock(long sellerId) {
        return new Stock(stockRepository.getAllBySellerId(sellerId));
    }

}

