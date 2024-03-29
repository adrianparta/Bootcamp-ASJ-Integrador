package com.bootcamp.proyectointegrador.Services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.DTOs.ProductoDTO;
import com.bootcamp.proyectointegrador.Exceptions.CategoriaNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProductoNotFoundException;
import com.bootcamp.proyectointegrador.Models.Categoria;
import com.bootcamp.proyectointegrador.Models.Producto;
import com.bootcamp.proyectointegrador.Models.Proveedor;
import com.bootcamp.proyectointegrador.Repositories.ProductoRepository;
import com.bootcamp.proyectointegrador.Repositories.ProveedorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	ProveedorRepository proveedorRepository;
	
	@Autowired
	CategoriaService categoriaService;
	
	public List<ProductoDTO> obtenerProductos(){
		try {
			List<Producto> productos =  productoRepository.findAll();
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
	
	public List<ProductoDTO> obtenerProductosPorEstado(Boolean estado){
		try {
			List<Producto> productos =  productoRepository.findByEstado(estado);
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
	
	public List<ProductoDTO> obtenerProductosPorProveedorYEstado(Integer id, Boolean estado){
		try {
			Proveedor proveedor = proveedorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El proveedor con el ID " + id + " no fue encontrado."));
			List<Producto> productos =  productoRepository.findByProveedorAndEstado(proveedor, estado);
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
	
	public List<ProductoDTO> obtenerProductosPorCategoriaYEstado(Integer id, Boolean estado){
		try {
			Categoria categoria = categoriaService.obtenerCategoria(id);
			List<Producto> productos =  productoRepository.findByCategoriaAndEstado(categoria, estado);
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
	        return productoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El producto con el ID " + id + " no fue encontrado."));
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
	        Proveedor proveedor = proveedorRepository.findById(productoDTO.getProveedorId()).orElseThrow(() -> new EntityNotFoundException("El proveedor no fue encontrado."));
	        Categoria categoria = categoriaService.obtenerCategoria(productoDTO.getCategoriaId());
	        if(!categoria.getEstado()) {
	            throw new RuntimeException("La categoria que intenta seleccionar está deshabilitada");
	        }
	        if(!proveedor.getEstado()) {
	        	throw new RuntimeException("El proveedor que intenta seleccionar está deshabilitado");
	        }
	        
	        Producto producto = new Producto(productoDTO, categoria, proveedor);
	        producto.setEstado(true);
	        productoRepository.save(producto);
	        
	        productoDTO = new ProductoDTO(producto);
	        return productoDTO;
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
	        Proveedor proveedor = proveedorRepository.findById(producto.getProveedorId()).orElseThrow(() -> new EntityNotFoundException("El proveedor con el ID " + producto.getProveedorId() + " no fue encontrado."));
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
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
	
}
