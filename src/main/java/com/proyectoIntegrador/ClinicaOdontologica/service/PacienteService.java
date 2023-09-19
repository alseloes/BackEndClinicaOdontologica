package com.proyectoIntegrador.ClinicaOdontologica.service;

import com.proyectoIntegrador.ClinicaOdontologica.entity.Paciente;
import com.proyectoIntegrador.ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    //MÉTODO PARA GUARDAR UN PACIENTE
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    //MÉTODO PARA BUSCAR UN PACIENTE POR ID

    public Optional<Paciente> buscarPacientePorID (Long id){
        return pacienteRepository.findById(id);
    }

    //MÉTODO PARA ELIMINAR UN PACIENTE POR ID
    public void eliminarPacientePorID (Long id){
        pacienteRepository.deleteById(id);
    }

    //MÉTODO PARA ACTUALIZAR UN PACIENTE
    public void actualizarPaciente (Paciente paciente){
        pacienteRepository.save(paciente);
    }

    //MÉTODO PARA LISTAR TODOS LOS PACIENTES
    public List<Paciente> listarPacientes (){
        return pacienteRepository.findAll();
    }

    //MÉTODO BUSCAR POR EMAIL A UN PACIENTE
    public Optional<Paciente> buscarPorEmail (String email) {
        return pacienteRepository.findByEmail(email);
    }

    //MÉTODO PARA BUSCAR PACIENTE POR DNI
    public Optional<Paciente> buscarPorDni (String dni){
        return pacienteRepository.findByDni(dni);
    }

}

