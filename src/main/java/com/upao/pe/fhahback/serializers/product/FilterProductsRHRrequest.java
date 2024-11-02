package com.upao.pe.fhahback.serializers.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterProductsRHRrequest {
    private List<String> brands = new ArrayList<>();
}
