package com.upao.pe.fhahback.services;

import com.upao.pe.fhahback.models.Size;
import com.upao.pe.fhahback.models.enums.Sizes;
import com.upao.pe.fhahback.repositories.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SizeService {
    @Autowired
    SizeRepository sizeRepository;

    public Size createSize(String sizes){
        Size size = new Size(null, Sizes.valueOf(sizes.toUpperCase()), new ArrayList<>());
        return sizeRepository.save(size);
    }

    public Size getSize(String sizes){
        Optional<Size> size = sizeRepository.findBySize(Sizes.valueOf(sizes.toUpperCase()));
        if(size.isEmpty()){
            throw new RuntimeException("Talla no encontrada");
        }
        return size.get();
    }

    public List<Size> getSizes(List<String> sizes){
        List<Sizes> sizesEnums = new ArrayList<>();
        sizes.forEach(size -> sizesEnums.add(Sizes.valueOf(size.toUpperCase())));
        return sizes.isEmpty() ? null : sizeRepository.findBySizeIn(sizesEnums);
    }

    public boolean existsSize(String sizes){
        return sizeRepository.existsBySize(Sizes.valueOf(sizes.toUpperCase()));
    }
}
