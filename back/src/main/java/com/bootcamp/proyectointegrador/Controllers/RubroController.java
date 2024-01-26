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
import com.bootcamp.proyectointegrador.Models.Rubro;
import com.bootcamp.proyectointegrador.Services.CategoriaService;
import com.bootcamp.proyectointegrador.Services.RubroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rubros")
public class RubroController {

	@Autowired
	RubroService rubroService;
	
	@GetMapping
	public ResponseEntity<List<Rubro>> getRubros(){
		return new ResponseEntity<List<Rubro>>(rubroService.obtenerRubros(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> postRubro(@Valid @RequestBody Rubro rubro, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(rubroService.crearRubro(rubro), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Rubro> deleteRubro(@PathVariable Integer id){
		return new ResponseEntity<Rubro>(rubroService.borrarRubro(id), HttpStatus.OK);
	}
	
	
	
}
