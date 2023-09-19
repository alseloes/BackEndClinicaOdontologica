package com.proyectoIntegrador.ClinicaOdontologica.repository;

import com.proyectoIntegrador.ClinicaOdontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

    Optional<Odontologo> findByNumeroDeMatricula(String numeroDeMatricula);
}
