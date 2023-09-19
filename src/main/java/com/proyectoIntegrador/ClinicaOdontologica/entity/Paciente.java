package com.proyectoIntegrador.ClinicaOdontologica.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private String nombre;
    @Column (nullable = false)
    private String apellido;
    @Column (nullable = false, unique=true)
    private String dni;
    @Column (nullable = false)
    private LocalDate fechaDeIngreso;
    @OneToOne(cascade= CascadeType.ALL )
    @JoinColumn(name= "domicilio_id", referencedColumnName = "id") //Unidirección
    private Domicilio domicilio;
    @Column(nullable = false)
    private String email;
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //Bidirección
    @JsonIgnore
    private Set<Turno> turnos= new HashSet<>();


    public Paciente(String nombre, String apellido, String dni, LocalDate fechaDeIngreso, Domicilio domicilio, String email, Set<Turno> turnos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeIngreso = fechaDeIngreso;
        this.domicilio = domicilio;
        this.email = email;
        this.turnos = turnos;
    }

    public Paciente(Long id, String nombre, String apellido, String dni, LocalDate fechaDeIngreso, Domicilio domicilio, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeIngreso = fechaDeIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    public Paciente(String nombre, String apellido, String dni, LocalDate fechaDeIngreso, Domicilio domicilio, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeIngreso = fechaDeIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }
}

