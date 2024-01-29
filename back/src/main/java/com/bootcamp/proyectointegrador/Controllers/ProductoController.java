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
import com.bootcamp.proyectointegrador.Exceptions.ProductoNotFoundException;
import com.bootcamp.proyectointegrador.Models.Producto;
import com.bootcamp.proyectointegrador.Models.Proveedor;
import com.bootcamp.proyectointegrador.Services.ProductoService;
import com.bootcamp.proyectointegrador.Services.ProveedorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	ProductoService productoService;
	
	@GetMapping
	public ResponseEntity<Object> getProductos() {
		try {
	        List<Producto> productos = productoService.obtenerProductos();
	        return new ResponseEntity<>(productos, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al obtener la lista de productos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProducto(@PathVariable Integer id) throws ProductoNotFoundException{
		try {
			Producto producto = productoService.obtenerProducto(id);
			return new ResponseEntity<>(producto, HttpStatus.OK);
		} catch (RuntimeException e){
			return new ResponseEntity<>("Error al obtener producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping
	public ResponseEntity<Object> postProducto(@Valid @RequestBody Producto producto, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
	        Producto nuevoProducto = productoService.agregarProducto(producto);
	        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al agregar producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProducto(@PathVariable Integer id) throws ProductoNotFoundException{
		try {
			Producto producto = productoService.borrarProducto(id);
			return new ResponseEntity<>(producto, HttpStatus.OK);	
		} catch(RuntimeException e) {
			return new ResponseEntity<>("Error al eliminar producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> putProducto(@PathVariable Integer id,@Valid @RequestBody Producto producto, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		try {
			Producto productoModificado = productoService.modificarProducto(id, producto);
			return new ResponseEntity<>(productoModificado, HttpStatus.OK);
		} catch (RuntimeException e) {
	        return new ResponseEntity<>("Error al modificar producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
	}
	
	
}
