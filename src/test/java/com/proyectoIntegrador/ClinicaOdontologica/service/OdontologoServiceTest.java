package com.proyectoIntegrador.ClinicaOdontologica.service;

import com.proyectoIntegrador.ClinicaOdontologica.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologoTest() {
        Odontologo odontologoGuardado = new Odontologo(
                "ABC123",
                "Doctor",
                "Muelas"
        );
        odontologoService.guardarOdontologo(odontologoGuardado);
        assertEquals(1L, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoPorIdTest() {
        Long idABuscar = 1L;
        Optional<Odontologo> odontologoABuscar = odontologoService.buscarOdontologoPorID(idABuscar);
        assertNotNull(odontologoABuscar.get());
    }

    @Test
    @Order(3)
    public void listarOdontologosTest() {
        List<Odontologo> odontologo = odontologoService.listarOdontologos();
        assertEquals(1, odontologo.size());
    }

    @Test
    @Order(4)
    public void actualizarOdontologoTest() {
        Odontologo odontologoActualizado = new Odontologo(
                1L,
                "XYZ098",
                "Mario",
                "Bross"
        );
        odontologoService.actualizarOdontologo(odontologoActualizado);
        Optional<Odontologo> odontologo = odontologoService.buscarOdontologoPorID(1L);
        assertEquals("Mario", odontologo.get().getNombre());
    }

    @Test
    @Order(5)
    public void eliminarOdontologoTest() {
        Long id = 1L;
        odontologoService.eliminarOdontologoPorID(id);
        Optional<Odontologo> odontologoEliminado = odontologoService.buscarOdontologoPorID(id);
        assertFalse(odontologoEliminado.isPresent());
    }
}
