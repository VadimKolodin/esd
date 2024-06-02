package com.example.testmp.goods;

import com.example.testmp.goods.data.GoodsRepository;
import com.example.testmp.goods.model.GoodView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository repository;

    public List<GoodView> getAllAvailable() {
        return repository.findAllAvailable().stream().map(g -> new GoodView(g.getId(), g.getName(), g.getDescription(), g.getPrice())).toList();
    }
}
