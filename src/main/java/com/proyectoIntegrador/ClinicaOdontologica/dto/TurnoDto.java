package com.proyectoIntegrador.ClinicaOdontologica.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
public class TurnoDto {


    private Long id;

    private Long pacienteId;

    private Long odontologoId;

    private LocalDate fecha;




}
