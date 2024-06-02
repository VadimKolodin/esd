package com.example.testmp.goods.orders;

import com.example.testmp.exception.ApiException;
import com.example.testmp.goods.orders.model.OrderDto;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderCreateService orderCreateService;

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getOrders();
    }

    @PostMapping
    public OrderDto create(@RequestParam Long goodId, @Positive @RequestParam Integer quantity) {
        return orderCreateService.createOrder(goodId, quantity);
    }

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ApiException.ApiExceptionMessage> handleException(ApiException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getApiMessage());
    }

}
