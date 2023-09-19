package com.proyectoIntegrador.ClinicaOdontologica.controller;

import com.proyectoIntegrador.ClinicaOdontologica.entity.Odontologo;
import com.proyectoIntegrador.ClinicaOdontologica.exception.BadRequestException;
import com.proyectoIntegrador.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.proyectoIntegrador.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService = new OdontologoService();

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodosLosOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @PostMapping
    public ResponseEntity<Odontologo> resgistrarOdontologoPorMatricula (@RequestBody Odontologo odontologo) throws BadRequestException {
        Optional<Odontologo> matriculaBuscada = odontologoService.buscarPorMatricula(odontologo.getNumeroDeMatricula());
        if (!matriculaBuscada.isPresent()){
            return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
        }else{
            throw new BadRequestException("matrícula", odontologo.getNumeroDeMatricula());
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorID(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontólogo con Id " + odontologo.getId() + " (" + odontologo.getNombre() + " "
                    + odontologo.getApellido() + ") se actualizó correctamente");
        } else {
            throw new ResourceNotFoundException("Odontólogo a actualizar con Id " + odontologo.getId() + " no encontrado");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId (@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorID(id);
        if(odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado);
        }else{
            throw new ResourceNotFoundException("Odontólogo buscado con Id " + id + " no encontrado");
        }
    }

    @GetMapping("/busqueda/{numeroDeMatricula}")
    public ResponseEntity<Optional<Odontologo>> buscarPorMatricula (@PathVariable String numeroDeMatricula) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(numeroDeMatricula);
        if(odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado);
        }else{
            throw new ResourceNotFoundException("Odontólogo buscado con matrícula " + numeroDeMatricula + " no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo (@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoAEliminar = odontologoService.buscarOdontologoPorID(id);
        if (odontologoAEliminar.isPresent()){
            odontologoService.eliminarOdontologoPorID(id);
            return ResponseEntity.ok("Odontólogo con Id " + id + " y turnos asociados a este eliminados exitosamente");
        }else{
            throw new ResourceNotFoundException("Odontólogo a eliminar con Id "+ id + " no encontrado");
        }
    }
}
