package com.conciertos.conciertosYa.controllers;

import com.conciertos.conciertosYa.model.Entrada;
import com.conciertos.conciertosYa.service.EntradaService;
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
@RequestMapping("/entradas")
public class EntradaController {

    private final EntradaService entradaService;

    @Autowired
    public EntradaController(EntradaService entradaService){
        this.entradaService = entradaService;
    }

    @PreAuthorize("hasRole('WRITE')")
    @PostMapping()
    @ApiOperation(value = "Crear un nuevo entrada", response = Entrada.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El entrada ha sido creado exitosamente", response = Entrada.class),
            @ApiResponse(code = 400, message = "La solicitud es incorrecta", response = Exception.class),
            @ApiResponse(code = 401, message = "No autorizado", response = Entrada.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Exception.class)})
    public Entrada crearEntrada(@RequestBody Entrada entrada) {
        return entradaService.guardarEntrada(entrada);
    }
    
    @PreAuthorize("hasRole('READ')")
    @GetMapping()
    @ApiOperation(value = "Obtener lista de los entradas registrados en la base de datos", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lista de entradas obtenida exitosamente", response = List.class),
            @ApiResponse(code = 401, message = "No autorizado", response = List.class),
            @ApiResponse(code = 403, message = "Prohibido", response = List.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = List.class)

    })
    public List<Entrada> obtenerEntradas() {
        return entradaService.obtenerEntradas();
    }

    @PreAuthorize("hasRole('READ')")
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una entrada por id", response = Entrada.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entrada obtenido exitosamente", response = Entrada.class),
            @ApiResponse(code = 401, message = "No autorizado", response = Entrada.class),
            @ApiResponse(code = 403, message = "Prohibido", response = Entrada.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o entrada inexistente", response = Exception.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Entrada.class)

    })
    public Entrada obtenerEntradaPorId(@PathVariable Integer id){
        return entradaService.obtenerEntradaPorId(id);
    }

    @PreAuthorize("hasRole('WRITE')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar una entrada de la base de datos", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entrada eliminado exitosamente", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "No autorizado", response = ResponseEntity.class),
            @ApiResponse(code = 403, message = "Prohibido", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o entrada inexistente", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = ResponseEntity.class)})
    public void eliminar(@PathVariable("id") Integer id) {
        entradaService.eliminar(id);
    }

    @PreAuthorize("hasRole('WRITE')")
    @PutMapping ()
    @ApiOperation(value = "Actualizar información de una entrada", response = Entrada.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entrada fue actualizado exitosamente", response = Entrada.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Exception.class),
            @ApiResponse(code = 404, message = "El Entrada no existe", response = Exception.class),
            @ApiResponse(code = 401, message = "No autorizado", response = Entrada.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Entrada.class)})
    public Entrada actualizarEntrada(@RequestBody Entrada entrada){
        return entradaService.actualizarEntrada(entrada);
    }
}


