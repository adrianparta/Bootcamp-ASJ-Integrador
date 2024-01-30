package com.bootcamp.proyectointegrador.Services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.DTOs.DetalleDTO;
import com.bootcamp.proyectointegrador.DTOs.OrdenDTO;
import com.bootcamp.proyectointegrador.DTOs.ProductoDTO;
import com.bootcamp.proyectointegrador.Exceptions.OrdenNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProductoNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProveedorNotFoundException;
import com.bootcamp.proyectointegrador.Models.Orden;
import com.bootcamp.proyectointegrador.Models.Producto;
import com.bootcamp.proyectointegrador.Models.Proveedor;
import com.bootcamp.proyectointegrador.Repositories.DetalleRepository;
import com.bootcamp.proyectointegrador.Repositories.OrdenRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrdenService {

	@Autowired
	OrdenRepository ordenRepository;
	
	@Autowired
	DetalleService detalleService;
	
	@Autowired
	ProveedorService proveedorService;
	
	public List<OrdenDTO> obtenerOrdenes(){
		try {
			List<Orden> ordenes =  ordenRepository.findByEstadoTrue();
	        List<OrdenDTO> ordenesDTO = new ArrayList<OrdenDTO>();
	        for (Orden orden : ordenes) {
				OrdenDTO ordenDTO = new OrdenDTO(orden);
				ordenesDTO.add(ordenDTO);
			}
	        return ordenesDTO;
		} catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la lista de ordenes.", e);
	    }
	       
	}
	
	public Orden obtenerOrden(Integer id) throws OrdenNotFoundException{
		try {
	        return ordenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La orden con el ID " + id + " no fue encontrada."));
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la orden con el ID " + id, e);
	    }	
	}
	
	public OrdenDTO obtenerOrdenDTO(Integer id) throws OrdenNotFoundException{
		try {
			Orden orden = this.obtenerOrden(id);
			OrdenDTO ordenDTO = new OrdenDTO(orden);
			List<DetalleDTO> detallesDTO = detalleService.obtenerDetallesPorOrden(id);
			ordenDTO.setDetalles(detallesDTO);
			return ordenDTO;
		} catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la orden con el ID " + id, e);
	    }	
	}
	
	public OrdenDTO agregarOrden(OrdenDTO ordenDTO) {
		try {
	        Proveedor proveedor = proveedorService.obtenerProveedor(ordenDTO.getProveedorId());
	        if(!proveedor.getEstado()) {
	        	throw new RuntimeException("El proveedor que intenta seleccionar está deshabilitado");
	        }
	  
	        Orden orden = new Orden(ordenDTO, proveedor);
	        Timestamp time = new Timestamp(System.currentTimeMillis());
	        orden.setFechaEmision(time);
	        orden.setEstado(true);
	        ordenRepository.save(orden);
	        
	        ordenDTO.setId(orden.getId());
	        ordenDTO.setFechaEmision(orden.getFechaEmision());
	        ordenDTO.setEstado(true);
	        ordenDTO.setProveedor(proveedor.getRazonSocial());
	        List<DetalleDTO> detallesDTO = new ArrayList<DetalleDTO>();
	        for (DetalleDTO detalleDTO : ordenDTO.getDetalles()) {
	        	detalleDTO.setOrdenId(orden.getId());
				DetalleDTO nuevoDetalleDTO = detalleService.agregarDetalle(detalleDTO);
				detallesDTO.add(nuevoDetalleDTO);
			}
	        ordenDTO.setDetalles(detallesDTO);
	        return ordenDTO;
	    } catch (ProveedorNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}
	
	public OrdenDTO borrarOrden(Integer id) throws OrdenNotFoundException{
		try {
			Orden orden = this.obtenerOrden(id);
			orden.setEstado(!orden.getEstado());
			ordenRepository.save(orden);
			
			OrdenDTO ordenDTO = new OrdenDTO(orden);
			return ordenDTO;
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
}