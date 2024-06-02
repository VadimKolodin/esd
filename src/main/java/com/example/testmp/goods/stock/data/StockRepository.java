package com.example.testmp.goods.stock.data;

import com.example.testmp.goods.model.GoodQuantityDto;
import com.example.testmp.user.seller.model.Stock;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockRepository {

    @PersistenceContext
    private EntityManager em;

    public List<GoodQuantityDto> getAllBySellerId(long sellerId) {
        String q = """
                   select new com.example.testmp.goods.model.GoodQuantityDto(
                    s.id.goodId,
                    s.quantity
                   ) 
                   from StockEntity s
                   where s.id.sellerId = :sellerId
                   """;
        return em.createQuery(q, GoodQuantityDto.class).setParameter("sellerId", sellerId).getResultList();
    }

}
