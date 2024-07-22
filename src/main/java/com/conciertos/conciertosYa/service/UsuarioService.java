package com.conciertos.conciertosYa.service;

import com.conciertos.conciertosYa.exception.ApiRequestException;
import com.conciertos.conciertosYa.model.Usuario;
import com.conciertos.conciertosYa.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuariosRepository usuariosRepository;

    @Autowired
    public UsuarioService(UsuariosRepository usuariosRepository){
        this.usuariosRepository = usuariosRepository;
    }

    public List<Usuario> obtenerUsuarios() {
        return this.usuariosRepository.findAll();
    }

    public Usuario crearUsuario(Usuario Usuario) {
        return this.usuariosRepository.save(Usuario);
    }

    public Usuario obtenerUsuarioCedula(Integer id) {
        final var usuario = this.usuariosRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new ApiRequestException("No existe un Usuario con la cédula número " + id);
        }
        return usuario.get();
    }

    public Usuario actualizarUsuario (Usuario Usuario) {
        return this.usuariosRepository.save(Usuario);
    }

    public void eliminar(Integer id) {
        this.usuariosRepository.deleteById(id);
    }
}
