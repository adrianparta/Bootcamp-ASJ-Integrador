package com.bootcamp.proyectointegrador.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.proyectointegrador.ErrorHandler;
import com.bootcamp.proyectointegrador.Models.Categoria;
import com.bootcamp.proyectointegrador.Models.Iva;
import com.bootcamp.proyectointegrador.Services.CategoriaService;
import com.bootcamp.proyectointegrador.Services.IvaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ivas")
public class IvaController {

	@Autowired
	IvaService ivaService;
	
	@GetMapping
	public ResponseEntity<Object> getIvas(){
		try {
	        List<Iva> ivas = ivaService.obtenerIvas();
	        return new ResponseEntity<>(ivas, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al obtener la lista de ivas: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
	}
}
