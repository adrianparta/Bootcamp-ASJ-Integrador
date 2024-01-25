package com.bootcamp.proyectointegrador.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.proyectointegrador.ErrorHandler;
import com.bootcamp.proyectointegrador.Models.Detalle;
import com.bootcamp.proyectointegrador.Models.Proveedor;
import com.bootcamp.proyectointegrador.Services.DetalleService;
import com.bootcamp.proyectointegrador.Services.ProveedorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/detalles")
public class DetalleController {

	@Autowired
	DetalleService detalleService;
	
	@GetMapping
	public ResponseEntity<Object> getDetalles() {
		return new ResponseEntity<>(detalleService.obtenerDetalles(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getDetalle(@PathVariable Integer id){
		return new ResponseEntity<>(detalleService.obtenerDetalle(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> postDetalle(@Valid @RequestBody Detalle detalle, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(detalleService.agregarDetalle(detalle), HttpStatus.CREATED);
	}
	
	
}
