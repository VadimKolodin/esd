package com.example.testmp.goods.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsRepository {

    @PersistenceContext
    private EntityManager em;

    public List<GoodEntity> findAllAvailable() {
        String q = "select distinct g from GoodEntity g inner join StockEntity s on s.id.goodId = g.id and s.quantity > 0";
        return em.createQuery(q, GoodEntity.class).getResultList();
    }

    public int findPriceById(long goodId) {
        return em.createQuery("select g.price from GoodEntity g where g.id = :id", Integer.class).setParameter("id", goodId).getSingleResult();
    }
}

