package com.proyectoIntegrador.ClinicaOdontologica.service;

import com.proyectoIntegrador.ClinicaOdontologica.dto.TurnoDto;
import com.proyectoIntegrador.ClinicaOdontologica.entity.Odontologo;
import com.proyectoIntegrador.ClinicaOdontologica.entity.Paciente;
import com.proyectoIntegrador.ClinicaOdontologica.entity.Turno;
import com.proyectoIntegrador.ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    //MÉTODO PARA GUARDAR UN TURNO
    public TurnoDto guardarTurno(Turno turno) {
        Turno turnoGuardado = turnoRepository.save(turno);
        return turnoATurnoDTO(turnoGuardado);
    }

    //MÉTODO PARA ACTUALIZAR UN TURNO
    public void actualizarTurno(Turno turno) {
        turnoRepository.save(turno);
    }

    /*public void actualizarTurno(TurnoDto turnoDto) {
        turnoRepository.save(turnoDTOaTurno(turnoDto));
    }*/

    /*public void actualizarTurno(Turno turno) {
        Turno turnoActualizado = turnoRepository.save(turno);
        return turnoATurnoDTO(turnoAcualizado);
    }*/


    //MÉTODO PARA BUSCAR TURNO POR ID
    public Optional<TurnoDto> buscarPorId(Long id){
        Optional<Turno> turnoEncontrado = turnoRepository.findById(id);
        if (turnoEncontrado.isPresent()){
            return Optional.of(turnoATurnoDTO(turnoEncontrado.get()));
        }else {
            return Optional.empty();
        }
    }

    //MÉTODO PARA ELIMINAR UN TURNO POR ID
    public void eliminarTurnoPorID(Long id) {
        turnoRepository.deleteById(id);
    }

    //MÉTODO PARA LISTAR TODOS LOS TURNOS
    public List<TurnoDto> ListarTurnos() {
        List<Turno> turnosRecuperados = turnoRepository.findAll();
        //Recorrer la lista para convertir de Turno a TurnoDto
        List<TurnoDto> respuesta = new ArrayList<>();
        for (Turno turno:turnosRecuperados) {
            respuesta.add(turnoATurnoDTO(turno));
        }
        return respuesta;
    }


    private TurnoDto turnoATurnoDTO(Turno turno){
        //Conversión manual, es decir, sin los métodos que me ofrece
        TurnoDto dto = new TurnoDto();
        //seteo de valores
        dto.setId(turno.getId());
        dto.setPacienteId(turno.getPaciente().getId());
        dto.setOdontologoId(turno.getOdontologo().getId());
        dto.setFecha(turno.getFecha());
        return dto;
    }



    private Turno turnoDTOaTurno(TurnoDto turnoDTO) {
        Odontologo odontologo = new Odontologo();
        Paciente paciente = new Paciente();
        Turno turno = new Turno();
        paciente.setId(turnoDTO.getPacienteId());
        odontologo.setId(turnoDTO.getOdontologoId());
        turno.setId(turnoDTO.getId());
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(turnoDTO.getFecha());
        return turno;
    }

}
