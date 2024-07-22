package com.conciertos.conciertosYa.repository;

import com.conciertos.conciertosYa.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<Evento, String> {
}

