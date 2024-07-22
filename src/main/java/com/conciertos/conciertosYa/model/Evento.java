package com.conciertos.conciertosYa.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private final String id;
    private final String descripcion;
    private final String escenario;
    private final String ciudad;
    private final String fecha;
    private final String genero;
    private final String responsable;
    private final String horaApertura;
    private final Boolean habilitado;
}

