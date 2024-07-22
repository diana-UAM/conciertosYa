package com.conciertos.conciertosYa.service;

import com.conciertos.conciertosYa.exception.ApiRequestException;
import com.conciertos.conciertosYa.model.Evento;
import com.conciertos.conciertosYa.repository.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventosRepository eventosRepository;

    @Autowired
    public EventoService(EventosRepository eventosRepository) {
        this.eventosRepository = eventosRepository;
    }

    public List<Evento> obtener() {
        return this.eventosRepository.findAll();
    }

    public Evento guardar(Evento evento) {
        return eventosRepository.save(evento);
    }

    public Evento obtener(String id) {
        Optional<Evento> evento = this.eventosRepository.findById(id);
        if (evento.isPresent()) {
            return evento.get();
        }
        throw new ApiRequestException("No existe un envío con número de guía " + id + " en el sistema");
    }

    public Evento actualizar(String id, Evento evento) {
        return this.eventosRepository.save(evento);
    }

    public void eliminar(String id) {
        this.eventosRepository.deleteById(id);
    }
}
