package com.example.testmp.goods.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodView {

    private long id;

    private String name;

    private String description;

    private Integer price;

}
