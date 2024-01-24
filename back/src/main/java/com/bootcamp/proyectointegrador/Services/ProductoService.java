package com.bootcamp.proyectointegrador.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Models.Producto;
import com.bootcamp.proyectointegrador.Models.Proveedor;
import com.bootcamp.proyectointegrador.Repositories.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository productoRepository;
	
	public List<Producto> obtenerProductos(){
		return productoRepository.findAll();
	}
	
	public Producto obtenerProducto(Integer id) {
		return productoRepository.findById(id).get();
	}
	
	public Producto agregarProducto(Producto producto) {
		return productoRepository.save(producto);
	}
	
	public Producto borrarProducto(Integer id) {
		Producto producto = productoRepository.findById(id).get();
		producto.setEstado(false);
		productoRepository.save(producto);
		return producto;  
	}
	
	public Producto modificarProducto(Integer id, Producto producto) {
		if(productoRepository.findById(id).get() != null) {
			return productoRepository.save(producto);
		}
		return null;
	}
	
}
