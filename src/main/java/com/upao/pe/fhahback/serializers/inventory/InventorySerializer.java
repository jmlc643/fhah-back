package com.upao.pe.fhahback.serializers.inventory;

import com.upao.pe.fhahback.serializers.color.ColorSerializer;
import com.upao.pe.fhahback.serializers.size.SizeSerializer;
import com.upao.pe.fhahback.serializers.supplier.SupplierSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventorySerializer {
    private int quantity;
    private SupplierSerializer supplier;
    private SizeSerializer size;
    private ColorSerializer color;
}
