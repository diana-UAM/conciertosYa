package com.conciertos.conciertosYa.model.Enums;

import jakarta.persistence.Table;

@Table(name = "estadoEnvio")
public enum EstadoEnvio {
    RECIBIDO, EN_RUTA, ENTREGADO;

    public static EstadoEnvio toEnum(String estado) {
        if (estado.equalsIgnoreCase("en ruta")) {
            return EstadoEnvio.EN_RUTA;
        }
        return EstadoEnvio.valueOf(estado.toUpperCase());
    }
}



