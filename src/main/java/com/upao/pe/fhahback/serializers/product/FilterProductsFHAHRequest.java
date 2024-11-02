package com.upao.pe.fhahback.serializers.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterProductsFHAHRequest {
    private List<String> colors = new ArrayList<>();
    private List<String> sizes = new ArrayList<>();
    private List<String> suppliers = new ArrayList<>();
}
