package com.bootcamp.proyectointegrador.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.proyectointegrador.Models.Usuario;
import com.bootcamp.proyectointegrador.Services.UsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuarios/")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("{usuario}/{contrasenia}")
	public ResponseEntity<Object> getAcceso(@PathVariable String usuario, @PathVariable String contrasenia){
		try {
	        Boolean acceso = usuarioService.verificarAcceso(usuario, contrasenia);
	        return new ResponseEntity<>(acceso, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Acceso denegado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
	}
}
