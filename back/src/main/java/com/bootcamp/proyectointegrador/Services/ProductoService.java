package com.bootcamp.proyectointegrador.Services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.DTOs.ProductoDTO;
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
	
	public List<ProductoDTO> obtenerProductos(){
		try {
			List<Producto> productos =  productoRepository.findByEstadoTrue();
	        List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
	        for (Producto producto : productos) {
				ProductoDTO productoDTO = new ProductoDTO(producto);
				productosDTO.add(productoDTO);
			}
	        return productosDTO;
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
	
	public ProductoDTO obtenerProductoDTO(Integer id) throws ProductoNotFoundException{
		try {
			Producto producto = this.obtenerProducto(id);
			ProductoDTO productoDTO = new ProductoDTO(producto);
			return productoDTO;
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener el producto con el ID " + id, e);
	    }	
	}
	
	public ProductoDTO agregarProducto(ProductoDTO productoDTO) {
		try {
			if(productoRepository.existsByCodigo(productoDTO.getCodigo())) {
				throw new RuntimeException("El codigo ya está en uso");
			}
	        Proveedor proveedor = proveedorService.obtenerProveedor(productoDTO.getProveedorId());
	        Categoria categoria = categoriaService.obtenerCategoria(productoDTO.getCategoriaId());
	        if(!categoria.getEstado()) {
	            throw new RuntimeException("La categoria que intenta seleccionar está deshabilitada");
	        }
	        if(!proveedor.getEstado()) {
	        	throw new RuntimeException("El proveedor que intenta seleccionar está deshabilitado");
	        }
	        
	        Producto producto = new Producto(productoDTO, categoria, proveedor);
	        productoRepository.save(producto);
	        
	        productoDTO.setId(producto.getId());
	        productoDTO.setEstado(true);
	        productoDTO.setProveedor(proveedor.getRazonSocial());
	        productoDTO.setCategoria(categoria.getCategoria());
	        
	        return productoDTO;
	    } catch (ProveedorNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (CategoriaNotFoundException e) {
	    	throw new RuntimeException(e.getMessage(), e);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}
	
	public ProductoDTO modificarEstadoProducto(Integer id) throws ProductoNotFoundException{
		try {
			Producto producto = this.obtenerProducto(id);
			producto.setEstado(!producto.getEstado());
			productoRepository.save(producto);
			
			ProductoDTO productoDTO = new ProductoDTO(producto);
			return productoDTO;
		} catch (ProductoNotFoundException e) {
	        throw new RuntimeException(e.getMessage());
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
	
	public ProductoDTO modificarProducto(Integer id, ProductoDTO producto) {
		try {
			Producto productoModificado = this.obtenerProducto(id);
			if(productoRepository.existsByCodigo(producto.getCodigo()) && !producto.getCodigo().equals(productoModificado.getCodigo())) {
				throw new RuntimeException("El codigo ya está en uso");
			}
			Timestamp time = new Timestamp(System.currentTimeMillis());
			productoModificado.setCodigo(producto.getCodigo());
			productoModificado.setUpdated_at(time);
			productoModificado.setNombre(producto.getNombre());
			productoModificado.setDescripcion(producto.getDescripcion());
			productoModificado.setPrecio(producto.getPrecio());
			productoModificado.setImagen_url(producto.getImagen_url());
			productoModificado.setEstado(producto.getEstado());
			Categoria categoria = categoriaService.obtenerCategoria(producto.getCategoriaId());
	        Proveedor proveedor = proveedorService.obtenerProveedor(producto.getProveedorId());
	        if(!categoria.getEstado() && categoria.getId() != productoModificado.getCategoria().getId()) {
	            throw new RuntimeException("La categoría que intenta seleccionar está deshabilitado");
	        }
	        if(!proveedor.getEstado() && proveedor.getId() != productoModificado.getProveedor().getId()) {
	            throw new RuntimeException("El proveedor que intenta seleccionar está deshabilitado");
	        }
	        productoModificado.setCategoria(categoria);
	        productoModificado.setProveedor(proveedor);
			productoRepository.save(productoModificado);
			
			ProductoDTO productoDTO = new ProductoDTO(productoModificado);
			return productoDTO;
		} catch (CategoriaNotFoundException e) {
	        throw new RuntimeException(e.getMessage());
		} catch (ProveedorNotFoundException e) {
	        throw new RuntimeException(e.getMessage());
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
	
}
