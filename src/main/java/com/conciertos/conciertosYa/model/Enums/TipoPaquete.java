package com.conciertos.conciertosYa.model.Enums;

import jakarta.persistence.Table;

@Table (name = "tipo_paquete")
public enum TipoPaquete {
    LIVIANO, MEDIANO, GRANDE
}

