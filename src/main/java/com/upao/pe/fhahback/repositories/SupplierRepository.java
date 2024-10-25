package com.upao.pe.fhahback.repositories;

import com.upao.pe.fhahback.models.Supplier;
import com.upao.pe.fhahback.models.enums.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findBySuppliersName(Suppliers supplierName);

    boolean existsBySuppliersName(Suppliers suppliers);
}
