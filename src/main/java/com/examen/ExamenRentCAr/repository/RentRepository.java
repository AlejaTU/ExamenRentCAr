package com.examen.ExamenRentCAr.repository;

import com.examen.ExamenRentCAr.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Alquiler, Long> {

    List<Alquiler> findByUserId_Id(Long userId);
}
