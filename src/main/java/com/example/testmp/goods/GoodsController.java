package com.example.testmp.goods;

import com.example.testmp.exception.ApiException;
import com.example.testmp.goods.model.GoodView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/goods")
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping("/available")
    public List<GoodView> getAllAvailable() {
        return goodsService.getAllAvailable();
    }

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ApiException.ApiExceptionMessage> handleException(ApiException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getApiMessage());
    }

}
