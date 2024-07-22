package com.conciertos.conciertosYa.model;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Id;

@Data
@Entity
public class Usuario {
    @Id
    private final Integer id;
    private final String nombreDeUsuario;
    private final String nombres;
    private final String apellidos;
    private final String tipoDeIdentificacion;
    private final String identificacion;
    private final String correoElectronico;
    private final String password;
}