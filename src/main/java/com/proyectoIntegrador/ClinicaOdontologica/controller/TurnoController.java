package com.proyectoIntegrador.ClinicaOdontologica.controller;

import com.proyectoIntegrador.ClinicaOdontologica.dto.TurnoDto;
import com.proyectoIntegrador.ClinicaOdontologica.entity.Odontologo;
import com.proyectoIntegrador.ClinicaOdontologica.entity.Paciente;
import com.proyectoIntegrador.ClinicaOdontologica.entity.Turno;
import com.proyectoIntegrador.ClinicaOdontologica.exception.BadRequestException;
import com.proyectoIntegrador.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.proyectoIntegrador.ClinicaOdontologica.service.OdontologoService;
import com.proyectoIntegrador.ClinicaOdontologica.service.PacienteService;
import com.proyectoIntegrador.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/turnos")
public class TurnoController {


    private TurnoService turnoService = new TurnoService();
    private PacienteService pacienteService = new PacienteService();
    private OdontologoService odontologoService = new OdontologoService();

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public ResponseEntity<List<TurnoDto>> ListarTodosLosTurnos() {
        return ResponseEntity.ok(turnoService.ListarTurnos());
    }

    @PostMapping
    public ResponseEntity<TurnoDto> registrarTurno(@RequestBody Turno turno) throws BadRequestException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorID(turno.getOdontologo().getId());
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(turno.getPaciente().getId());
        Boolean condicionesAdecuadas = (odontologoBuscado.isPresent() && pacienteBuscado.isPresent());
        if (condicionesAdecuadas) {
            //Entonces ambos existen
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            String salida = "= ambos existentes en el sistema, ";
            throw new BadRequestException("'paciente y odontólogo'",salida);
        }
    }

   /* @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDto turnoDto) throws ResourceNotFoundException {
        Optional<TurnoDto> turnoBuscado = turnoService.buscarPorId(turnoDto.getId());
        if (turnoBuscado.isPresent()) {
            turnoService.actualizarTurno(turnoService.turnoDTOaTurno(turnoDto));
            return ResponseEntity.ok("Turno: id = " + turnoDto.getId() + ", se actualizó exitosamente");
        } else {
            throw new ResourceNotFoundException("Turno a actualizar no encontrado");
        }
    }*/


    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTurno(@PathVariable Long id, @RequestBody Turno turno) throws ResourceNotFoundException {
        Optional<TurnoDto> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()) {
            turno.setId(id);
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("El turno con Id = " + id + " se actualizó correctamente");
        } else {
            throw new ResourceNotFoundException("Turno a actualizar no encontrado");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<TurnoDto> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoBuscado.get());
        } else {
            throw new ResourceNotFoundException("Turno buscado con Id " + id + " no encontrado");
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<TurnoDto> turnoBuscado =turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()){
            turnoService.eliminarTurnoPorID(id);
            return ResponseEntity.ok("Turno eliminado con éxito");
        } else {
            throw new ResourceNotFoundException("Turno a eliminar con Id "+ id + " no encontrado");
        }
    }
}
