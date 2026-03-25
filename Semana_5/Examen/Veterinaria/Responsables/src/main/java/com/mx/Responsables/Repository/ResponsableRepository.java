package com.mx.Responsables.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Responsables.Entity.Responsable;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Long> {
    List<Responsable> findByVeterinariaId(Long veterinariaId);
}