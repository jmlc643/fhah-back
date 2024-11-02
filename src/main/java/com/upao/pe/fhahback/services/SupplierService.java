package com.upao.pe.fhahback.services;

import com.upao.pe.fhahback.models.Supplier;
import com.upao.pe.fhahback.models.enums.Suppliers;
import com.upao.pe.fhahback.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Supplier createSupplier(String supplierName){
        Supplier supplier = new Supplier(null, Suppliers.valueOf(supplierName.toUpperCase()), new ArrayList<>());
        return supplierRepository.save(supplier);
    }

    public Supplier getSupplier(String supplierName){
        Optional<Supplier> supplier = supplierRepository.findBySuppliersName(Suppliers.valueOf(supplierName.toUpperCase()));
        if(supplier.isEmpty()){
            throw new RuntimeException("Proveedor no encontrado");
        }
        return supplier.get();
    }

    public List<Supplier> getSuppliers(List<String> supplierNames){
        List<Suppliers> suppliersEnums = new ArrayList<>();
        supplierNames.forEach(supplierName -> suppliersEnums.add(Suppliers.valueOf(supplierName.toUpperCase())));
        return supplierNames.isEmpty() ? null : supplierRepository.findBySuppliersNameIn(suppliersEnums);
    }

    public boolean existsSupplier(String supplierName){
        return supplierRepository.existsBySuppliersName(Suppliers.valueOf(supplierName.toUpperCase()));
    }
}
