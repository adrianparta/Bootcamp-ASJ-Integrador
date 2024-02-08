package com.bootcamp.proyectointegrador.Services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.DTOs.DetalleDTO;
import com.bootcamp.proyectointegrador.DTOs.OrdenDTO;
import com.bootcamp.proyectointegrador.Exceptions.OrdenNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProveedorNotFoundException;
import com.bootcamp.proyectointegrador.Models.Orden;
import com.bootcamp.proyectointegrador.Models.Producto;
import com.bootcamp.proyectointegrador.Models.Proveedor;
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
	
	@Autowired
	ProductoService productoService;
	
	public List<OrdenDTO> obtenerOrdenes(){
		try {
			List<Orden> ordenes =  ordenRepository.findAll();
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
	
	public List<OrdenDTO> obtenerOrdenesPorEstado(Boolean estado){
		try {
			List<Orden> ordenes =  ordenRepository.findByEstado(estado);
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
	        List<DetalleDTO> detallesDTO = new ArrayList<DetalleDTO>();
	        for (DetalleDTO detalleDTO : ordenDTO.getDetalles()) {
	        	detalleDTO.setOrdenId(orden.getId());
	        	Producto producto  = productoService.obtenerProducto(detalleDTO.getProductoId());
	        	if(producto.getProveedor().getId() != proveedor.getId()) {
	        		throw new RuntimeException("Todos los productos deben pertenecer al mismo proveedor");
	        	}
	        	DetalleDTO nuevoDetalleDTO = detalleService.agregarDetalle(detalleDTO);
	        	detallesDTO.add(nuevoDetalleDTO);
	        }
	        ordenDTO.setDetalles(detallesDTO);
	        Double total = 0.0;
	        for (DetalleDTO detalleDTO : detallesDTO) {
				total += detalleDTO.getPrecio_unitario() * detalleDTO.getCantidad();
			}
	        orden.setTotal(total);
	        ordenRepository.save(orden);
	        
	        ordenDTO.setTotal(total);
	        ordenDTO.setId(orden.getId());
	        ordenDTO.setFechaEmision(orden.getFechaEmision());
	        ordenDTO.setEstado(true);
	        ordenDTO.setProveedor(proveedor.getRazonSocial());
	        return ordenDTO;
	    } catch (ProveedorNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}
	
	public OrdenDTO modificarEstadoOrden(Integer id) throws OrdenNotFoundException{
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