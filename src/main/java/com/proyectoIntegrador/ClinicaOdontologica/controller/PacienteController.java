package com.proyectoIntegrador.ClinicaOdontologica.controller;


import com.proyectoIntegrador.ClinicaOdontologica.entity.Paciente;
import com.proyectoIntegrador.ClinicaOdontologica.exception.BadRequestException;
import com.proyectoIntegrador.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.proyectoIntegrador.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService = new PacienteService();

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodosLosPacientes(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPacientePoDNI (@RequestBody Paciente paciente) throws BadRequestException {
        Optional<Paciente> dniBuscado = pacienteService.buscarPorDni(paciente.getDni());
        if (!dniBuscado.isPresent()){
            return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
        }else{
            throw new BadRequestException("DNI", paciente.getDni());
        }
    }


    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente con Id " + paciente.getId() + " (" + paciente.getNombre() + " "
                    + paciente.getApellido() + ") se actualizó correctamente");
        }else{
            //return ResponseEntity.notFound().build();
            throw new ResourceNotFoundException("Paciente a actualizar con Id " + paciente.getId() + " no encontrado");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(id);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);
        }else {
            throw new ResourceNotFoundException("Paciente buscado con Id " + id + " no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteAEliminar = pacienteService.buscarPacientePorID(id);
        if (pacienteAEliminar.isPresent()){
            pacienteService.eliminarPacientePorID(id);
            return ResponseEntity.ok("Paciente con Id " + id + " y turnos asociados a este eliminados exitosamente");
        }else{
            throw new ResourceNotFoundException("Paciente a eliminar con Id "+ id + " no encontrado");
        }
    }

    @GetMapping("/email/{email}")
    public Optional<ResponseEntity<Paciente>> buscarPorEmail(@PathVariable String email) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);
        if(pacienteBuscado.isPresent()){
            return Optional.of(ResponseEntity.ok(pacienteBuscado.get())); //REVISAR
        }else {
            throw new ResourceNotFoundException("Paciente buscado con email "+ email + " no encontrado");
        }
    }

    @GetMapping("/dni/{dni}")
    public Optional<ResponseEntity<Paciente>> buscarPorDni(@PathVariable String dni) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorDni(dni);
        if(pacienteBuscado.isPresent()){
            return Optional.of(ResponseEntity.ok(pacienteBuscado.get())); //REVISAR
        }else {
            throw new ResourceNotFoundException("Paciente buscado con DNI " + dni + " no encontrado");
        }
    }



    /*  COMENTADO PQ YA NO SE ESTÁ USANDO THYMELEAF @GetMapping
    public String buscarPacientePorEmail (Model model, @RequestParam("email") String email) {
        Paciente paciente = pacienteService.buscarPorEmail(email);
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        return "index";
    }*/


}
