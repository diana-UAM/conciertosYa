package com.conciertos.conciertosYa.service;

import com.conciertos.conciertosYa.exception.ApiRequestException;
import com.conciertos.conciertosYa.model.Entrada;
import com.conciertos.conciertosYa.repository.EntradasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaService {

    private final EntradasRepository entradasRepository;

    @Autowired
    public EntradaService(EntradasRepository entradasRepository){
        this.entradasRepository = entradasRepository;
    }

    public Entrada guardarEntrada(Entrada entrada) {
        return this.entradasRepository.save(entrada);
    }

    public List<Entrada> obtenerEntradas() {
        return this.entradasRepository.findAll();
    }

    public Entrada obtenerEntradaPorId(Integer id) {
        final var entrada = this.entradasRepository.findById(id);
        if (entrada.isEmpty()){
            throw new ApiRequestException("No existe una entrada con este id");
        }
        return entrada.get();
    }

    public Entrada actualizarEntrada (Entrada entrada) {
        return this.entradasRepository.save(entrada);
    }

    public void eliminar(Integer id) {
        this.entradasRepository.deleteById(id);
    }
}
