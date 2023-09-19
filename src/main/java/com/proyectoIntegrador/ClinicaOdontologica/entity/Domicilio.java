package com.proyectoIntegrador.ClinicaOdontologica.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "domicilios")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String calle;
    @Column (nullable = false)
    private String numero;
    @Column (nullable = false)
    private String localidad;
    @Column (nullable = false)
    private String provincia;
    //@OneToOne(mappedBy = "domicilio_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //private Paciente paciente = new Paciente();

    public Domicilio(String calle, String numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }
}
