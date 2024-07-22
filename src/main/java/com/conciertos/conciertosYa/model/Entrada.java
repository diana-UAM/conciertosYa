package com.conciertos.conciertosYa.model;

import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class Entrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private final Integer id;
    private final String descripcion;
    private final boolean habilitado;
}

