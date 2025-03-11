package com.examen.ExamenRentCAr.controller;

import com.examen.ExamenRentCAr.model.Alquiler;
import com.examen.ExamenRentCAr.repository.CocheRepository;
import com.examen.ExamenRentCAr.repository.UserRepository;
import com.examen.ExamenRentCAr.service.RentService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alquileres")
public class RentController {

    private final RentService rentService;
    private final CocheRepository cocheRepository;
    private final UserRepository userRepository;

    public RentController(RentService rentService, CocheRepository cocheRepository, UserRepository userRepository) {
        this.rentService = rentService;
        this.cocheRepository = cocheRepository;
        this.userRepository = userRepository;
    }
//• POST /rentals → Un usuario puede alquilar un coche (Verificar disponibilidad).
    @PostMapping
    public ResponseEntity<Alquiler> createRent(@RequestBody Alquiler alquiler) {
        return ResponseEntity.ok(rentService.createAlquiler(alquiler));
    }

        @GetMapping
        public ResponseEntity<List<Alquiler>> getAllRents() {
            return ResponseEntity.ok(rentService.getAllRents());
        }
//PUT /rentals/{id}/return → Un usuario puede devolver un coche (Actualizar disponibilidad).

    @PutMapping("/{id}/return")
    public ResponseEntity<Alquiler> returnRental(@PathVariable Long id) {
        return rentService.returnRental(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//get by id
@GetMapping("/users/{id}/rentals")
public ResponseEntity<List<Alquiler>> getRentalsByUserId(@PathVariable Long id) {
    List<Alquiler> alquileres = rentService.getRentalsByUserId(id);
    if (alquileres.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(alquileres);
}


}


