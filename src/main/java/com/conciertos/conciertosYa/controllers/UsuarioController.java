package com.conciertos.conciertosYa.controllers;

import com.conciertos.conciertosYa.model.Usuario;
import com.conciertos.conciertosYa.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PreAuthorize("hasRole('WRITE')")
    @PostMapping()
    @ApiOperation(value = "Crear un nuevo usuario", response = Usuario.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El Usuario ha sido creado exitosamente", response = Usuario.class),
            @ApiResponse(code = 400, message = "La solicitud es incorrecta", response = Exception.class),
            @ApiResponse(code = 401, message = "No autorizado", response = Usuario.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Exception.class)})

    public Usuario crearUsuario(@RequestBody Usuario Usuario) {
        return usuarioService.crearUsuario(Usuario);
    }

    @PreAuthorize("hasRole('READ')")
    @GetMapping()
    @ApiOperation(value = "Obtener lista de los usuarios registrados en la base de datos", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lista de Usuarios obtenida exitosamente", response = List.class),
            @ApiResponse(code = 401, message = "No autorizado", response = List.class),
            @ApiResponse(code = 403, message = "Prohibido", response = List.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = List.class)

    })
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @PreAuthorize("hasRole('READ')")
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un usuario por cédula", response = Usuario.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario obtenido exitosamente", response = Usuario.class),
            @ApiResponse(code = 401, message = "No autorizado", response = Usuario.class),
            @ApiResponse(code = 403, message = "Prohibido", response = Usuario.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o Usuario inexistente", response = Exception.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Usuario.class)
    })
    public Usuario obtenerUsuarioPorCedula(@PathVariable Integer id){
        return usuarioService.obtenerUsuarioCedula(id);
    }

    @PreAuthorize("hasRole('WRITE')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar un Usuario de la base de datos", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario eliminado exitosamente", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "No autorizado", response = ResponseEntity.class),
            @ApiResponse(code = 403, message = "Prohibido", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o Usuario inexistente", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = ResponseEntity.class)})
    public void eliminarUsuario(@PathVariable("id") Integer id) {
        usuarioService.eliminar(id);
    }

    @PreAuthorize("hasRole('WRITE')")
    @PutMapping ()
    @ApiOperation(value = "Actualizar información de un usuario", response = Usuario.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario fue actualizado exitosamente", response = Usuario.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Exception.class),
            @ApiResponse(code = 404, message = "El usuario no existe", response = Exception.class),
            @ApiResponse(code = 401, message = "No autorizado", response = Usuario.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Usuario.class)})
    public Usuario actualizarUsuario(@RequestBody Usuario Usuario) {
        return usuarioService.actualizarUsuario(Usuario);
    }
}
