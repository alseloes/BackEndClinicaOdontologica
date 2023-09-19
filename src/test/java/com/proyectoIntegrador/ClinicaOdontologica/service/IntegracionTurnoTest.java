package com.proyectoIntegrador.ClinicaOdontologica.service;

import com.proyectoIntegrador.ClinicaOdontologica.dto.TurnoDto;
import com.proyectoIntegrador.ClinicaOdontologica.entity.Domicilio;
import com.proyectoIntegrador.ClinicaOdontologica.entity.Odontologo;
import com.proyectoIntegrador.ClinicaOdontologica.entity.Paciente;
import com.proyectoIntegrador.ClinicaOdontologica.entity.Turno;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnoTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    private void cargarDatos() {
        Paciente paciente = pacienteService.guardarPaciente(
                new Paciente(
                        "Juan Carlos",
                        "Alvarez",
                        "JCAS654",
                        LocalDate.of(2023, 9, 10),
                        new Domicilio(
                                "Carrera 38",
                                "26-501",
                                "Medellín",
                                "Antioquia"
                        ),
                        "alseloes@gmail.com"
                )
        );
        Odontologo odontologo = odontologoService.guardarOdontologo(
                new Odontologo(
                        "ABC123",
                        "Mario",
                        "Bross"
                )
        );
        TurnoDto turnoDto = new TurnoDto();
        turnoDto.setPacienteId(paciente.getId());
        turnoDto.setOdontologoId(odontologo.getId());
        turnoDto.setFecha(LocalDate.of(2023, 10, 10));
        turnoService.guardarTurno(turnoDTOaTurno(turnoDto));
    }

    @Test
    public void listarTurnosTest() throws Exception {
        cargarDatos();
        MvcResult respuesta = mockMvc
                .perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    private Turno turnoDTOaTurno(TurnoDto turnoDTO) {
        Odontologo odontologo = new Odontologo();
        Paciente paciente = new Paciente();
        Turno turno = new Turno();
        odontologo.setId(turnoDTO.getOdontologoId());
        paciente.setId(turnoDTO.getPacienteId());
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        return turno;
    }
}
