package com.examen.ExamenRentCAr.service;

import com.examen.ExamenRentCAr.model.Coche;
import com.examen.ExamenRentCAr.model.Usuario;
import com.examen.ExamenRentCAr.repository.CocheRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CocheService {

    private final CocheRepository cocheRepository;


    public CocheService(CocheRepository cocheRepository) {
        this.cocheRepository = cocheRepository;
    }

    //crear coche
    public Coche createCoche(Coche coche) {
        return cocheRepository.save(coche);
    }

    //consultar lista de coches
    public List<Coche> getAllCoches() {
        return cocheRepository.findAll();
    }

    //update coche
    public Optional<Coche> updateCoche(Long id, Coche cocheDetails) {
        return cocheRepository.findById(id).map(coche -> {
            coche.setPlateNumber(cocheDetails.getPlateNumber());
            coche.setBrand(cocheDetails.getBrand());
            coche.setModel(cocheDetails.getModel());
            coche.setYear(cocheDetails.getYear());
            coche.setAvailable(cocheDetails.getAvailable());
            coche.setApellido(cocheDetails.getApellido());

            return cocheRepository.save(coche);
        });
    }

    //eliminar coche
    public boolean deleteCoche(Long id) {
        if(cocheRepository.existsById(id)) {
            cocheRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
