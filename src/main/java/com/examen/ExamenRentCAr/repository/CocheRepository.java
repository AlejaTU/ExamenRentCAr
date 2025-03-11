package com.examen.ExamenRentCAr.repository;


import com.examen.ExamenRentCAr.model.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Long> {


}
