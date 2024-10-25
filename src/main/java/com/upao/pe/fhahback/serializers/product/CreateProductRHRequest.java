package com.upao.pe.fhahback.serializers.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateProductRHRequest {
    private String code;
    private String name;
    private int quantity;
    private double purchasePrice;
    private double salesPrice;
    private String brandName;
}
