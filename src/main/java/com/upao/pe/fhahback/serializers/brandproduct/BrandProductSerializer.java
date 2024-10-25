package com.upao.pe.fhahback.serializers.brandproduct;

import com.upao.pe.fhahback.serializers.brand.BrandSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrandProductSerializer {
    private int quantity;
    private BrandSerializer brand;
}
