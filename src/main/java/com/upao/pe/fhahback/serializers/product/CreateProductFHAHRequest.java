package com.upao.pe.fhahback.serializers.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateProductFHAHRequest {
    private String code;
    private double purchasePrice;
    private String name;
    private String model;
    private double salesPrice;
    private int quantity;
    private String supplierName;
    private String size;
    private String color;
}
