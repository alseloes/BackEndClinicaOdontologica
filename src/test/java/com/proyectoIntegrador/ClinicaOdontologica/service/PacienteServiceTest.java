package com.proyectoIntegrador.ClinicaOdontologica.service;

import com.proyectoIntegrador.ClinicaOdontologica.entity.Domicilio;
import com.proyectoIntegrador.ClinicaOdontologica.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest() {
        Paciente pacienteGuardado = new Paciente(
                "Juan Carlos",
                "Alvarez",
                "8362AB",
                LocalDate.of(2023, 9, 15),
                new Domicilio(
                        "Carrera 38",
                        "26-501",
                        "Medellín",
                        "Antioquia"
                ),
                "alseloes@gmail.com"
        );
        pacienteService.guardarPaciente(pacienteGuardado);
        assertEquals(1L, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorIdTest() {
        Long idABuscar = 1L;
        Optional<Paciente> pacienteABuscar = pacienteService.buscarPacientePorID(idABuscar);
        assertNotNull(pacienteABuscar.get());
    }

    @Test
    @Order(3)
    public void listarPacientesTest() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        assertEquals(1, pacientes.size());
    }

    @Test
    @Order(4)
    public void actualizarPacienteTest() {
        Paciente pacienteActualizado = new Paciente(
                1L,
                "Elisa",
                "Carlaccini",
                "ABC567",
                LocalDate.of(2023, 9, 11),
                new Domicilio(
                        "Calle 16A sur",
                        "9E - 50",
                        "Medellín",
                        "Antioquia"
                ),
                "mauricio@gmail.com"
        );
        pacienteService.actualizarPaciente(pacienteActualizado);
        Optional<Paciente> paciente = pacienteService.buscarPacientePorID(1L);
        assertEquals("Elisa", paciente.get().getNombre());

    }

    @Test
    @Order(5)
    public void eliminarPacienteTest() {
        Long id = 1L;
        pacienteService.eliminarPacientePorID(id);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPacientePorID(id);
        assertFalse(pacienteEliminado.isPresent());
    }
}