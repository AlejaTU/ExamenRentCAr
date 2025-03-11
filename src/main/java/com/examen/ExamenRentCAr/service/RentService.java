package com.examen.ExamenRentCAr.service;

import com.examen.ExamenRentCAr.model.Alquiler;
import com.examen.ExamenRentCAr.model.Coche;
import com.examen.ExamenRentCAr.model.Status;
import com.examen.ExamenRentCAr.repository.CocheRepository;
import com.examen.ExamenRentCAr.repository.RentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {
    private final RentRepository rentRepository;
    private final CocheRepository cocheRepository;

    public RentService(RentRepository rentRepository, CocheRepository cocheRepository) {
        this.rentRepository = rentRepository;
        this.cocheRepository = cocheRepository;
    }
//• POST /rentals → Un usuario puede alquilar un coche (Verificar disponibilidad).
    //crear alquiler
    public Alquiler createAlquiler(Alquiler rent) {
        return rentRepository.save(rent);
    }

    //consultar lista de alquileres
    public List<Alquiler> getAllRents() {
        return rentRepository.findAll();
    }

//PUT /rentals/{id}/return → Un usuario puede devolver un coche (Actualizar disponibilidad).
    @Transactional
    public Optional<Alquiler> returnRental(Long rentalId) {
        return rentRepository.findById(rentalId).map(alquiler -> {
            alquiler.setStatus(Status.COMPLETED); // Cambia status a finalizado
            alquiler.setReturnDate(LocalDateTime.now()); // Fecha de devolución actual

            // Actualiza la disponibilidad del coche
            Coche coche = alquiler.getCarId();
            coche.setAvailable(true);
            cocheRepository.save(coche);

            return rentRepository.save(alquiler);
        });
    }


    //GET /users/{id}/rentals → Obtener el historial de alquileres de un usuario.
    public List<Alquiler> getRentalsByUserId(Long userId) {
        return rentRepository.findByUserId_Id(userId);
    }


}
