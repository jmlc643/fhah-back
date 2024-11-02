package com.upao.pe.fhahback.repositories;

import com.upao.pe.fhahback.models.Color;
import com.upao.pe.fhahback.models.Inventory;
import com.upao.pe.fhahback.models.Size;
import com.upao.pe.fhahback.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByColorIn(List<Color> colors);

    List<Inventory> findBySizeIn(List<Size> sizes);

    List<Inventory> findBySupplierIn(List<Supplier> suppliers);
}