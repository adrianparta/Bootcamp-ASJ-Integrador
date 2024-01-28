package com.bootcamp.proyectointegrador.Controllers;

import java.util.List;
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
import com.bootcamp.proyectointegrador.Exceptions.ProveedorNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProvinciaNotFoundException;
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
		try {
	        List<Proveedor> proveedores = proveedorService.obtenerProveedores();
	        return new ResponseEntity<>(proveedores, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al obtener la lista de proveedores: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProveedor(@PathVariable Integer id) throws ProveedorNotFoundException{
		try {
			Proveedor proveedor = proveedorService.obtenerProveedor(id);
			return new ResponseEntity<>(proveedor, HttpStatus.OK);
		} catch (RuntimeException e){
			return new ResponseEntity<>("Error al obtener un proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> postProveedor(@Valid @RequestBody Proveedor proveedor, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
	        Proveedor nuevoProveedor = proveedorService.agregarProveedor(proveedor);
	        return new ResponseEntity<>(nuevoProveedor, HttpStatus.CREATED);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al agregar proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProveedor(@PathVariable Integer id) throws ProveedorNotFoundException{
		try {
			Proveedor proveedor = proveedorService.borrarProveedor(id);
			return new ResponseEntity<>(proveedor, HttpStatus.OK);	
		} catch(RuntimeException e) {
			return new ResponseEntity<>("Error al eliminar proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> putProveedor(@PathVariable Integer id, @Valid @RequestBody Proveedor proveedor, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
			Proveedor proveedorModificado = proveedorService.modificarProveedor(id, proveedor);
			return new ResponseEntity<>(proveedorModificado, HttpStatus.OK);
		} catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al modificar proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	
}
