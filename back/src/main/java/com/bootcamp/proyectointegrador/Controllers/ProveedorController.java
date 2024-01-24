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
import com.bootcamp.proyectointegrador.Models.Proveedor;
import com.bootcamp.proyectointegrador.Services.ProveedorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

	@Autowired
	ProveedorService proveedorService;
	
	@GetMapping
	public ResponseEntity<Object> getProveedores() {
		return new ResponseEntity<>(proveedorService.obtenerProveedores(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProveedor(@PathVariable Integer id){
		return new ResponseEntity<>(proveedorService.obtenerProveedor(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> postProveedor(@Valid @RequestBody Proveedor proveedor, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(proveedorService.agregarProveedor(proveedor), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProveedor(@PathVariable Integer id){
		return new ResponseEntity<>(proveedorService.borrarProveedor(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> putProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedor){
		return new ResponseEntity<>(proveedorService.modificarProveedor(id, proveedor), HttpStatus.CREATED);
	}
	
	
	
}
