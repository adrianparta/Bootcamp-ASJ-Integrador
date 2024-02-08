package com.bootcamp.proyectointegrador.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.proyectointegrador.ErrorHandler;
import com.bootcamp.proyectointegrador.DTOs.OrdenDTO;
import com.bootcamp.proyectointegrador.Exceptions.OrdenNotFoundException;
import com.bootcamp.proyectointegrador.Services.OrdenService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ordenes/")
public class OrdenController {

	@Autowired
	OrdenService ordenService;
	
	@GetMapping
	public ResponseEntity<Object> getOrdenes() {
		try {
	        List<OrdenDTO> ordenes = ordenService.obtenerOrdenes();
	        return new ResponseEntity<>(ordenes, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al obtener la lista de productos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("estado/{estado}")
	public ResponseEntity<Object> getOrdenesByEstado(@PathVariable Boolean estado) {
		try {
	        List<OrdenDTO> ordenes = ordenService.obtenerOrdenesPorEstado(estado);
	        return new ResponseEntity<>(ordenes, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al obtener la lista de productos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> getOrden(@PathVariable Integer id) throws OrdenNotFoundException{
		try {
			OrdenDTO ordenDTO = ordenService.obtenerOrdenDTO(id);
			return new ResponseEntity<>(ordenDTO, HttpStatus.OK);
		} catch (RuntimeException e){
			return new ResponseEntity<>("Error al obtener orden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping
	public ResponseEntity<Object> postOrden(@Valid @RequestBody OrdenDTO ordenDTO, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
	        OrdenDTO nuevaOrdenDTO = ordenService.agregarOrden(ordenDTO);
	        return new ResponseEntity<>(nuevaOrdenDTO, HttpStatus.CREATED);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al agregar orden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> deleteActivateOrden(@PathVariable Integer id) throws OrdenNotFoundException{
		try {
			OrdenDTO ordenDTO = ordenService.modificarEstadoOrden(id);
			return new ResponseEntity<>(ordenDTO, HttpStatus.OK);	
		} catch(RuntimeException e) {
			return new ResponseEntity<>("Error al eliminar/activar orden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
}
