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
import com.bootcamp.proyectointegrador.Services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getCategorias(){
		return new ResponseEntity<List<Categoria>>(categoriaService.obtenerCategorias(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> postCategoria(@Valid @RequestBody Categoria categoria, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(categoriaService.crearCategoria(categoria), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Categoria> deleteCategoria(@PathVariable Integer id){
		return new ResponseEntity<Categoria>(categoriaService.borrarCategoria(id), HttpStatus.OK);
	}
	
	
	
}
