package com.proyectoIntegrador.ClinicaOdontologica.service;

import com.proyectoIntegrador.ClinicaOdontologica.entity.Odontologo;
import com.proyectoIntegrador.ClinicaOdontologica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;

    //MÉTODO PARA GUARDAR UN ODONTOLOGO
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    //MÉTODO PARA BUSCAR UN ODONTOLOGO POR ID

    public Optional<Odontologo> buscarOdontologoPorID (Long id){
        return odontologoRepository.findById(id);
    }

    //MÉTODO PARA ELIMINAR UN ODONTOLOGO POR ID
    public void eliminarOdontologoPorID (Long id){
        odontologoRepository.deleteById(id);
    }

    //MÉTODO PARA ACTUALIZAR UN ODONTOLOGO
    public void actualizarOdontologo (Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }

    //MÉTODO PARA LISTAR TODOS LOS ODONTOLOGOS
    public List<Odontologo> listarOdontologos (){
        return odontologoRepository.findAll();
    }

    //MÉTODO PARA BUSCAR POR MATRICULA
    public Optional<Odontologo> buscarPorMatricula(String numeroDeMatricula){
        return odontologoRepository.findByNumeroDeMatricula(numeroDeMatricula);
    }

}

