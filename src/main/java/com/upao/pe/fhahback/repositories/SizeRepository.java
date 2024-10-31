package com.upao.pe.fhahback.repositories;

import com.upao.pe.fhahback.models.Size;
import com.upao.pe.fhahback.models.enums.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    Optional<Size> findBySize(Sizes sizes);

    boolean existsBySize(Sizes sizes);

    List<Size> findBySizeIn(List<Sizes> size);
}
