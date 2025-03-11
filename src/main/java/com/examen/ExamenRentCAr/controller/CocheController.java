package com.examen.ExamenRentCAr.controller;

import com.examen.ExamenRentCAr.model.Coche;
import com.examen.ExamenRentCAr.service.CocheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coches")
public class CocheController {

    private final CocheService cocheService;


    public CocheController(CocheService cocheService) {
        this.cocheService = cocheService;
    }

    //crear coche
    @PostMapping
    public ResponseEntity<Coche> createCoche(@RequestBody Coche coche) {
        Coche createdCoche = cocheService.createCoche(coche);
        return ResponseEntity.ok(createdCoche);
    }


    //consultar los coches
    @GetMapping
    public ResponseEntity<List<Coche>> getAllCoches() {
        return ResponseEntity.ok(cocheService.getAllCoches());
    }


    //actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Coche> updateCoche(@PathVariable Long id, @RequestBody Coche cocheDetails) {
        Optional<Coche> cocheActualizado = cocheService.updateCoche(id, cocheDetails);
        return cocheActualizado
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoche(@PathVariable Long id) {
        return cocheService.deleteCoche(id) ? ResponseEntity.noContent().build()  : ResponseEntity.notFound().build();
    }

}
