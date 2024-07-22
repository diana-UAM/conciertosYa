package com.conciertos.conciertosYa.model.Enums;

import jakarta.persistence.Table;

@Table (name = "tipo_Usuario")
public enum TipoUsuario {
    CONDUCTOR, REPARTIDOR, COORDINADOR
}
