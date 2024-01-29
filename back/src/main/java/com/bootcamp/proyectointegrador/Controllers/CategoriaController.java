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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.proyectointegrador.ErrorHandler;
import com.bootcamp.proyectointegrador.Exceptions.CategoriaNotFoundException;
import com.bootcamp.proyectointegrador.Models.Categoria;
import com.bootcamp.proyectointegrador.Services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<Object> getCategorias(){
		try {
	        List<Categoria> categorias = categoriaService.obtenerCategorias();
	        return new ResponseEntity<>(categorias, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al obtener la lista de categorías: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }		
	}
	
	@PostMapping
	public ResponseEntity<Object> postCategoria(@Valid @RequestBody Categoria categoria, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
	        Categoria categoriaNueva = categoriaService.crearCategoria(categoria);
	        return new ResponseEntity<>(categoriaNueva, HttpStatus.CREATED);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al agregar categoria: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCategoria(@PathVariable Integer id) throws CategoriaNotFoundException{
		try {
			Categoria categoria = categoriaService.borrarCategoria(id);
			return new ResponseEntity<>(categoria, HttpStatus.OK);	
		} catch(RuntimeException e) {
			return new ResponseEntity<>("Error al eliminar categoria: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	
	
}
