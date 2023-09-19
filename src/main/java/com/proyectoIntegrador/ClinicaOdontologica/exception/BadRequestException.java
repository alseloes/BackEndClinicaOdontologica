package com.proyectoIntegrador.ClinicaOdontologica.exception;

public class BadRequestException extends Exception {
    public BadRequestException(String resourceName, String resourceValue) {
        super(String.format("El recurso necesario %s con el valor '%s' ya existe en el sistema o no cumple las condiciones para registrarse.", resourceName, resourceValue));
    }
}
