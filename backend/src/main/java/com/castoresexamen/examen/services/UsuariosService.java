package com.castoresexamen.examen.services;

import com.castoresexamen.examen.repositories.UsuariosRepository;
import com.castoresexamen.examen.models.UsuariosModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public UsuariosModel login(String correo, String contrasena) {
        
        return usuariosRepository.findByCorreoAndContrasena(correo, contrasena);
    }
}
