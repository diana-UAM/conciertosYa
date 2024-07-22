package com.conciertos.conciertosYa.controllers;

import com.conciertos.conciertosYa.model.Usuario;
import com.conciertos.conciertosYa.model.Evento;
import com.conciertos.conciertosYa.service.EventoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService){
        this.eventoService = eventoService;
    }

    @PreAuthorize("hasRole('WRITE')")
    @PostMapping()
    @ApiOperation(value = "Crear evento", response = Evento.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Evento creado exitosamente", response = Evento.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Exception.class),
            @ApiResponse(code = 401, message = "No autorizado", response = Evento.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Evento.class)
    })
    public Evento guardarEvento(@RequestBody Evento evento) {
        return eventoService.guardar(evento);

    }

    @PreAuthorize("hasRole('READ')")
    @GetMapping()
    @ApiOperation(value = "Obtener lista de los eventos registrados en la base de datos", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lista de eventos obtenida exitosamente", response = List.class),
            @ApiResponse(code = 401, message = "No autorizado", response = List.class),
            @ApiResponse(code = 403, message = "Prohibido", response = List.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = List.class)
    })
    public List<Evento> obtenerEvento() {
        return eventoService.obtener();
    }

    @PreAuthorize("hasRole('READ')")
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un evento", response = Evento.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El evento fue encontrado", response = Evento.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Exception.class),
            @ApiResponse(code = 401, message = "No autorizado", response = Usuario.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o eventoinexistente", response = Exception.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Evento.class)
    })
    public Evento obtenerEventoPorId(@PathVariable String id){
        return eventoService.obtener(id);
    }

    @PreAuthorize("hasRole('WRITE')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar un evento de la base de datos", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Evento eliminado exitosamente", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "No autorizado", response = ResponseEntity.class),
            @ApiResponse(code = 403, message = "Prohibido", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o eventoinexistente", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = ResponseEntity.class)})

    public void eliminar(@PathVariable String id) {
        eventoService.eliminar(id);
    }

    @PreAuthorize("hasRole('WRITE')")
    @PutMapping ("/{id}")
    @ApiOperation(value = "Actualizar estado de un evento", response = Evento.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Estado del evento fue actualizado exitosamente", response = Evento.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Exception.class),
            @ApiResponse(code = 404, message = "El eventono existe", response = Exception.class),
            @ApiResponse(code = 401, message = "No autorizado", response = Exception.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Exception.class)})
    public Evento actualizarEvento(@PathVariable String id, @RequestBody Evento solicitud)
    {
        return eventoService.actualizar(id, solicitud);
    }
}