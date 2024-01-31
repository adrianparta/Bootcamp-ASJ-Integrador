package com.bootcamp.proyectointegrador.Controllers;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.proyectointegrador.ErrorHandler;
import com.bootcamp.proyectointegrador.DTOs.ProveedorDTO;
import com.bootcamp.proyectointegrador.Exceptions.ProveedorNotFoundException;
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
	        List<ProveedorDTO> proveedores = proveedorService.obtenerProveedores();
	        return new ResponseEntity<>(proveedores, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al obtener la lista de proveedores: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProveedor(@PathVariable Integer id) throws ProveedorNotFoundException{
		try {
			ProveedorDTO proveedor = proveedorService.obtenerProveedorDTO(id);
			return new ResponseEntity<>(proveedor, HttpStatus.OK);
		} catch (RuntimeException e){
			return new ResponseEntity<>("Error al obtener proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> postProveedor(@Valid @RequestBody ProveedorDTO proveedorDTO, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
	        ProveedorDTO nuevoProveedorDTO = proveedorService.agregarProveedor(proveedorDTO);
	        return new ResponseEntity<>(nuevoProveedorDTO, HttpStatus.CREATED);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al agregar proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PutMapping("/{id}/estado")
	public ResponseEntity<Object> deleteActivateProveedor(@PathVariable Integer id) throws ProveedorNotFoundException{
		try {
			ProveedorDTO proveedor = proveedorService.modificarEstadoProveedor(id);
			return new ResponseEntity<>(proveedor, HttpStatus.OK);	
		} catch(RuntimeException e) {
			return new ResponseEntity<>("Error al eliminar proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> putProveedor(@PathVariable Integer id, @Valid @RequestBody ProveedorDTO proveedorDTO, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
			ProveedorDTO proveedorModificado = proveedorService.modificarProveedor(id, proveedorDTO);
			return new ResponseEntity<>(proveedorModificado, HttpStatus.OK);
		} catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al modificar proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	
}
