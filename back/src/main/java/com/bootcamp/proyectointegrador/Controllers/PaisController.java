package com.bootcamp.proyectointegrador.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.proyectointegrador.Models.Iva;
import com.bootcamp.proyectointegrador.Models.Pais;
import com.bootcamp.proyectointegrador.Models.Provincia;
import com.bootcamp.proyectointegrador.Services.PaisService;
import com.bootcamp.proyectointegrador.Services.ProvinciaService;

@RestController
@RequestMapping("/paises")
public class PaisController {

	@Autowired
	PaisService paisService;
	
	@GetMapping
	public ResponseEntity<Object> getPaises(){
		try {
	        List<Pais> paises = paisService.obtenerPaises();
	        return new ResponseEntity<>(paises, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al obtener la lista de paises: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
	}
}
