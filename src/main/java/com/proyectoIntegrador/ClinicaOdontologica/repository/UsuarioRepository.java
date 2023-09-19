package com.proyectoIntegrador.ClinicaOdontologica.repository;

import com.proyectoIntegrador.ClinicaOdontologica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String correo);

}
