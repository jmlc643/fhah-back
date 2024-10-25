package com.upao.pe.fhahback.services;

import com.upao.pe.fhahback.models.Brand;
import com.upao.pe.fhahback.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    public Brand createBrand(String brandName){
        Brand brand = new Brand(null, brandName, new ArrayList<>());
        return brandRepository.save(brand);
    }

    public Brand getBrand(String brandName){
        Optional<Brand> brand = brandRepository.findByBrandName(brandName);
        if(brand.isEmpty()){
            throw new RuntimeException("Marca no encontrada");
        }
        return brand.get();
    }

    public boolean existsBrand(String brandName){
        return brandRepository.existsByBrandName(brandName);
    }
}
