package com.upao.pe.fhahback.serializers.product;

import com.upao.pe.fhahback.serializers.brandproduct.BrandProductSerializer;
import com.upao.pe.fhahback.serializers.inventory.InventorySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductSerializer {
    private String code;
    private String barcode;
    private String name;
    private String model;
    private double purchasePrice;
    private double salesPrice;
    private String photo;
    private List<InventorySerializer> inventories;
    private List<BrandProductSerializer> brands;
}
