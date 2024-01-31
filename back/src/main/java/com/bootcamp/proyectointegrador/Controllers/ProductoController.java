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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.proyectointegrador.ErrorHandler;
import com.bootcamp.proyectointegrador.DTOs.ProductoDTO;
import com.bootcamp.proyectointegrador.Exceptions.ProductoNotFoundException;
import com.bootcamp.proyectointegrador.Services.ProductoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	ProductoService productoService;
	
	@GetMapping
	public ResponseEntity<Object> getProductos() {
		try {
	        List<ProductoDTO> productosDTO = productoService.obtenerProductos();
	        return new ResponseEntity<>(productosDTO, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al obtener la lista de productos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProducto(@PathVariable Integer id) throws ProductoNotFoundException{
		try {
			ProductoDTO productoDTO = productoService.obtenerProductoDTO(id);
			return new ResponseEntity<>(productoDTO, HttpStatus.OK);
		} catch (RuntimeException e){
			return new ResponseEntity<>("Error al obtener producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping
	public ResponseEntity<Object> postProducto(@Valid @RequestBody ProductoDTO productoDTO, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
	        ProductoDTO nuevoProductoDTO = productoService.agregarProducto(productoDTO);
	        return new ResponseEntity<>(nuevoProductoDTO, HttpStatus.CREATED);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al agregar producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
	}
	
	@PutMapping("/{id}/estado")
	public ResponseEntity<Object> deleteOrActivateProducto(@PathVariable Integer id) throws ProductoNotFoundException{
		try {
			ProductoDTO productoDTO = productoService.modificarEstadoProducto(id);
			return new ResponseEntity<>(productoDTO, HttpStatus.OK);	
		} catch(RuntimeException e) {
			return new ResponseEntity<>("Error al modificar el estado del producto producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> putProducto(@PathVariable Integer id,@Valid @RequestBody ProductoDTO productoDTO, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
			ProductoDTO productoDTOModificado = productoService.modificarProducto(id, productoDTO);
			return new ResponseEntity<>(productoDTOModificado, HttpStatus.OK);
		} catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al modificar producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
	}
	
	
}
