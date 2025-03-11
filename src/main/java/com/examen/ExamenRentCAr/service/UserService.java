package com.examen.ExamenRentCAr.service;


import com.examen.ExamenRentCAr.model.Usuario;
import com.examen.ExamenRentCAr.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //Crear usuario

    public Usuario createUser(Usuario usuario) {
        return userRepository.save(usuario);
    }

    //consultar lista usuarios

    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }


    //update usuario

    public Optional<Usuario> updateUser(Long id, Usuario usuarioDetails) {
        return userRepository.findById(id).map(usuario -> {
            usuario.setUsername(usuarioDetails.getUsername());
            usuario.setEmail(usuarioDetails.getEmail());
            usuario.setLicenseNumber(usuarioDetails.getLicenseNumber());
            usuario.setIniciales(usuarioDetails.getIniciales());
            usuario.setNombre(usuarioDetails.getNombre());
            return userRepository.save(usuario);
        });
    }


    //delete usuario
    public boolean deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }




}
