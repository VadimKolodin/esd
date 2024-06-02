package com.example.testmp.goods.orders.data;

import com.example.testmp.goods.model.GoodQuantityDto;
import com.example.testmp.goods.orders.model.OrderDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersRepository {

    @PersistenceContext
    private EntityManager em;

    public OrderEntity persist(long goodId, long sellerId, long clientId, int quantity) {
        OrderEntityId id = new OrderEntityId(goodId, sellerId, clientId);
        OrderEntity orderEntity = new OrderEntity(id, quantity);
        em.persist(orderEntity);
        return orderEntity;
    }

    public List<GoodQuantityDto> getCartByClientId(long clientId) {
        String q = """
                   select new com.example.testmp.goods.model.GoodQuantityDto(
                    s.id.goodId,
                    s.quantity
                   ) 
                   from OrderEntity s
                   where s.id.clientId = :clientId
                   """;
        return em.createQuery(q, GoodQuantityDto.class).setParameter("clientId", clientId).getResultList();
    }

    public List<OrderDto> getAllByClientId(long clientId) {
        String q = """
                   select new com.example.testmp.goods.orders.model.OrderDto(
                    s.id.goodId,
                    s.id.sellerId,
                    s.id.clientId,
                    s.quantity
                   ) 
                   from OrderEntity s
                   where s.id.clientId = :clientId
                   """;
        return em.createQuery(q, OrderDto.class).setParameter("clientId", clientId).getResultList();
    }

    public List<OrderDto> getAllBySellerId(long sellerId) {
        String q = """
                   select new com.example.testmp.goods.orders.model.OrderDto(
                    s.id.goodId,
                    s.id.sellerId,
                    s.id.clientId,
                    s.quantity
                   ) 
                   from OrderEntity s
                   where s.id.sellerId = :sellerId
                   """;
        return em.createQuery(q, OrderDto.class).setParameter("sellerId", sellerId).getResultList();
    }


}

