package com.bootcamp.proyectointegrador.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.proyectointegrador.ErrorHandler;
import com.bootcamp.proyectointegrador.Models.Orden;
import com.bootcamp.proyectointegrador.Models.Producto;
import com.bootcamp.proyectointegrador.Services.OrdenService;
import com.bootcamp.proyectointegrador.Services.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

	@Autowired
	OrdenService ordenService;
	
	@GetMapping
	public ResponseEntity<Object> getOrdenes() {
		return new ResponseEntity<>(ordenService.obtenerOrdenes(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOrden(@PathVariable Integer id){
		return new ResponseEntity<>(ordenService.obtenerOrden(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> postOrden(@Valid @RequestBody Orden orden, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(ordenService.agregarOrden(orden), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteOrden(@PathVariable Integer id){
		return new ResponseEntity<>(ordenService.borrarOrden(id), HttpStatus.OK);
	}
	
}
