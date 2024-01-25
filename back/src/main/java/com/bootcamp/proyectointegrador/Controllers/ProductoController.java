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
	public ResponseEntity<List<Producto>> getProductos() {
		return new ResponseEntity<List<Producto>>(productoService.obtenerProductos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProducto(@PathVariable Integer id){
		return new ResponseEntity<Producto>(productoService.obtenerProducto(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> postProducto(@Valid @RequestBody Producto producto, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().validation(bindingResult);
			
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(productoService.agregarProducto(producto), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Producto> deleteProducto(@PathVariable Integer id){
		return new ResponseEntity<Producto>(productoService.borrarProducto(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Producto> putProducto(@PathVariable Integer id, @RequestBody Producto producto){
		return new ResponseEntity<Producto>(productoService.modificarProducto(id, producto), HttpStatus.CREATED);
	}
	
	
	
}
