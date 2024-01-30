package com.bootcamp.proyectointegrador.Services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.DTOs.DetalleDTO;
import com.bootcamp.proyectointegrador.Exceptions.ProductoNotFoundException;
import com.bootcamp.proyectointegrador.Models.Detalle;
import com.bootcamp.proyectointegrador.Models.Orden;
import com.bootcamp.proyectointegrador.Models.Producto;
import com.bootcamp.proyectointegrador.Repositories.DetalleRepository;
import com.bootcamp.proyectointegrador.Repositories.OrdenRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DetalleService {

	@Autowired
	DetalleRepository detalleRepository;
	
	@Autowired
	OrdenRepository ordenRepository;
	
	@Autowired
	ProductoService productoService;
	
	public List<DetalleDTO> obtenerDetallesPorOrden(Integer id){
		try {
			Orden orden = ordenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La orden con el ID " + id + " no fue encontrada."));
			List<Detalle> detalles = detalleRepository.findByOrden(orden);
	        List<DetalleDTO> detallesDTO = new ArrayList<DetalleDTO>();
	        for (Detalle detalle : detalles) {
				DetalleDTO detalleDTO = new DetalleDTO(detalle);
				detallesDTO.add(detalleDTO);
			}
	        return detallesDTO;
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la lista de detalles.", e);
	    }	
	}
	
	
	public DetalleDTO agregarDetalle(DetalleDTO detalleDTO) {
		try {
	        Orden orden = ordenRepository.findById(detalleDTO.getOrdenId()).orElseThrow(() -> new EntityNotFoundException("La orden no fue encontrada."));
	        Producto producto = productoService.obtenerProducto(detalleDTO.getProductoId());
	        if(!orden.getEstado()) {
	        	throw new RuntimeException("La orden está cancelada");
	        }
	        if(!producto.getEstado()) {
	        	throw new RuntimeException("El producto está cancelado");
	        }
	        
	        Detalle detalle = new Detalle(detalleDTO, orden, producto);
	        detalle.setPrecio_unitario(producto.getPrecio());
	        detalleRepository.save(detalle);
	        
	        detalleDTO = new DetalleDTO(detalle);
	        return detalleDTO;
	    } catch (ProductoNotFoundException e) {
	    	throw new RuntimeException(e.getMessage(), e);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}
	

}
