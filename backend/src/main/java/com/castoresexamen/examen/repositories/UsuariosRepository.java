package com.castoresexamen.examen.repositories;

import com.castoresexamen.examen.models.UsuariosModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long> {
    UsuariosModel findByCorreoAndContrasena(String correo, String contrasena);
}