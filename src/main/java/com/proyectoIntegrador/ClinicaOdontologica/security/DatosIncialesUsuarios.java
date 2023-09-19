package com.proyectoIntegrador.ClinicaOdontologica.security;


import com.proyectoIntegrador.ClinicaOdontologica.entity.Usuario;
import com.proyectoIntegrador.ClinicaOdontologica.entity.UsuarioRole;
import com.proyectoIntegrador.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIncialesUsuarios implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //necesito crear un usuario como si fuese real
        //necesito guardarlo en la bdd
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passCinfrar = "prueba";
        String passCifrado = cifrador.encode(passCinfrar);
        System.out.println("Clave cifrada: " + passCifrado);
        //crear un usuario
        Usuario usuario1 = new Usuario("Juan Carlos", "admin", "alseloes@gmail.com", passCifrado, UsuarioRole.ROLE_ADMIN);
        Usuario usuario2 = new Usuario("Elisa", "elisa", "correo@correo.com", passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
    }
}
