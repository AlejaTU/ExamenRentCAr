package com.examen.ExamenRentCAr.controller;

import com.examen.ExamenRentCAr.model.Usuario;
import com.examen.ExamenRentCAr.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UserService userService;


    public UsuarioController(UserService userService) {
        this.userService = userService;
    }


    //crear una tarea
    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario user) {
        Usuario createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    //consultar todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    //actualizar un usuario

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(
            @PathVariable Long id,
            @RequestBody Usuario usuarioDetails) {

        Optional<Usuario> usuarioActualizado = userService.updateUser(id, usuarioDetails);

        return usuarioActualizado
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


}


