package com.upao.pe.fhahback.serializers.product;

import com.upao.pe.fhahback.serializers.brandproduct.BrandProductSerializer;
import com.upao.pe.fhahback.serializers.inventory.InventorySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailsProductSerializer {
    private String code;
    private String name;
    private String model;
    private double purchasePrice;
    private double salesPrice;
    private InventorySerializer inventory;
    private BrandProductSerializer brand;
}
