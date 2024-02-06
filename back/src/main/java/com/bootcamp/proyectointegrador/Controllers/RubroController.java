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
import com.bootcamp.proyectointegrador.Exceptions.RubroNotFoundException;
import com.bootcamp.proyectointegrador.Models.Categoria;
import com.bootcamp.proyectointegrador.Models.Rubro;
import com.bootcamp.proyectointegrador.Services.RubroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rubros/")
@CrossOrigin(origins = "http://localhost:4200")
public class RubroController {

	@Autowired
	RubroService rubroService;
	
	@GetMapping
	public ResponseEntity<Object> getRubros(){
		try {
	        List<Rubro> rubros = rubroService.obtenerRubros();
	        return new ResponseEntity<>(rubros, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al obtener la lista de rubros: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> getRubro(@PathVariable Integer id) throws RubroNotFoundException{
		try {
	        Rubro rubro = rubroService.obtenerRubro(id);
	        return new ResponseEntity<>(rubro, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al obtener rubro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
	}
	
	@GetMapping("activos")
	public ResponseEntity<Object> getRubrosActivos(){
		try {
	        List<Rubro> rubros = rubroService.obtenerRubrosActivos();
	        return new ResponseEntity<>(rubros, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al obtener la lista de rubros: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }		
	}
	
	@PostMapping()
	public ResponseEntity<Object> postRubro(@Valid @RequestBody Rubro rubro, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
			Rubro rubroNuevo = rubroService.crearRubro(rubro);
	        return new ResponseEntity<>(rubroNuevo, HttpStatus.CREATED);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al agregar rubro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> putRubro(@PathVariable Integer id,@Valid @RequestBody Rubro rubro, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
			Rubro rubroModificado = rubroService.modificarRubro(id, rubro);
			return new ResponseEntity<>(rubroModificado, HttpStatus.OK);
		} catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al modificar rubro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
	}
	
	
	
}
