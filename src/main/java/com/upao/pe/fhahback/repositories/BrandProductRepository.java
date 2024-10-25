package com.upao.pe.fhahback.repositories;

import com.upao.pe.fhahback.models.BrandProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandProductRepository extends JpaRepository<BrandProduct, Long> {
}
