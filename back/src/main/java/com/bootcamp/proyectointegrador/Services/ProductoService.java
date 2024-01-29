package com.bootcamp.proyectointegrador.Services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Exceptions.CategoriaNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProductoNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProveedorNotFoundException;
import com.bootcamp.proyectointegrador.Models.Categoria;
import com.bootcamp.proyectointegrador.Models.Producto;
import com.bootcamp.proyectointegrador.Models.Proveedor;
import com.bootcamp.proyectointegrador.Repositories.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	ProveedorService proveedorService;
	
	@Autowired
	CategoriaService categoriaService;
	
	public List<Producto> obtenerProductos(){
		try {
	        return productoRepository.findByEstadoTrue();
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la lista de productos.", e);
	    }	
	}
	
	public Producto obtenerProducto(Integer id) throws ProductoNotFoundException{
		try {
	        return productoRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("El producto con el ID " + id + " no fue encontrado."));
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener el producto con el ID " + id, e);
	    }	
	}
	
	public Producto agregarProducto(Producto producto) {
		try {
			if(productoRepository.existsByCodigo(producto.getCodigo())) {
				throw new RuntimeException("El codigo ya está en uso");
			}
	        Proveedor proveedor = proveedorService.obtenerProveedor(producto.getProveedor().getId());
	        Categoria categoria = categoriaService.obtenerCategoria(producto.getCategoria().getId());
	        if(!categoria.getEstado()) {
	            throw new RuntimeException("La categoria que intenta seleccionar está deshabilitada");
	        }
	        if(!proveedor.getEstado()) {
	        	throw new RuntimeException("El proveedor que intenta seleccionar está deshabilitado");
	        }
	        Timestamp time = new Timestamp(System.currentTimeMillis());
	        producto.setCreated_at(time);
	        producto.setUpdated_at(time);
	        producto.setEstado(true);
	        producto.setProveedor(proveedor);
	        producto.setCategoria(categoria);
	        return productoRepository.save(producto);
	    } catch (ProveedorNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (CategoriaNotFoundException e) {
	    	throw new RuntimeException(e.getMessage(), e);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}
	
	public Producto borrarProducto(Integer id) throws ProductoNotFoundException{
		try {
			Producto producto = this.obtenerProducto(id);
			producto.setEstado(false);
			return productoRepository.save(producto);
		} catch (ProductoNotFoundException e) {
	        throw new RuntimeException(e.getMessage());
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
	
	public Producto modificarProducto(Integer id, Producto producto) {
		try {
			Producto productoModificado = this.obtenerProducto(id);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			productoModificado.setUpdated_at(time);
			productoModificado.setNombre(producto.getNombre());
			productoModificado.setDescripcion(producto.getDescripcion());
			productoModificado.setPrecio(producto.getPrecio());
			productoModificado.setImagen_url(producto.getImagen_url());
			productoModificado.setEstado(producto.getEstado());
			Categoria categoria = categoriaService.obtenerCategoria(producto.getCategoria().getId());
	        Proveedor proveedor = proveedorService.obtenerProveedor(producto.getProveedor().getId());
	        if(!categoria.getEstado() && categoria.getId() != productoModificado.getCategoria().getId()) {
	            throw new RuntimeException("La categoría que intenta seleccionar está deshabilitado");
	        }
	        if(!proveedor.getEstado() && proveedor.getId() != productoModificado.getProveedor().getId()) {
	            throw new RuntimeException("El proveedor que intenta seleccionar está deshabilitado");
	        }
	        productoModificado.setCategoria(categoria);
	        productoModificado.setProveedor(proveedor);
			return productoRepository.save(productoModificado);
		} catch (CategoriaNotFoundException e) {
	        throw new RuntimeException(e.getMessage());
		} catch (ProveedorNotFoundException e) {
	        throw new RuntimeException(e.getMessage());
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
	
}
