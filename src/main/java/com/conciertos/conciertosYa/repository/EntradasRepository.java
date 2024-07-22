package com.conciertos.conciertosYa.repository;

import com.conciertos.conciertosYa.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradasRepository extends JpaRepository<Entrada, Integer> {
}
