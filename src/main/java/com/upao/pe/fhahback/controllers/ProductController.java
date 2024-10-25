package com.upao.pe.fhahback.controllers;

import com.upao.pe.fhahback.serializers.product.CreateProductFHAHRequest;
import com.upao.pe.fhahback.serializers.product.CreateProductRHRequest;
import com.upao.pe.fhahback.serializers.product.ProductSerializer;
import com.upao.pe.fhahback.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/list/")
    public List<ProductSerializer> listProducts(){return productService.listProducts();}

    @GetMapping("/list/fhah/")
    public List<ProductSerializer> listProductsFHAH(){return productService.listProductsFHAH();}

    @GetMapping("/list/rh/")
    public List<ProductSerializer> listProductsRH(){return productService.listProductsRH();}

    @PostMapping("/create/fhah/")
    public ProductSerializer createProductFHAH(@RequestBody CreateProductFHAHRequest request){
        return productService.createProductFHAH(request);
    }

    @PostMapping("/create/rh/")
    public ProductSerializer createProductRH(@RequestBody CreateProductRHRequest request){
        return productService.createProductRH(request);
    }
}
