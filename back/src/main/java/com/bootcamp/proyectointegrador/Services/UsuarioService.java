package com.bootcamp.proyectointegrador.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Boolean verificarAcceso(String usuario, String contrasenia){
		try {
	        return usuarioRepository.existsByUsuarioAndContrasenia(usuario, contrasenia);
	    } catch (Exception e) {
	        throw new RuntimeException("Acceso denegado.", e);
	    }	
	}
}
