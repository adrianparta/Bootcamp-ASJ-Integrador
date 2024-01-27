package com.bootcamp.proyectointegrador.Services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Exceptions.IvaNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProveedorNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProvinciaNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.RubroNotFoundException;
import com.bootcamp.proyectointegrador.Models.Iva;
import com.bootcamp.proyectointegrador.Models.Proveedor;
import com.bootcamp.proyectointegrador.Models.Provincia;
import com.bootcamp.proyectointegrador.Models.Rubro;
import com.bootcamp.proyectointegrador.Repositories.ProveedorRepository;
import com.bootcamp.proyectointegrador.Repositories.ProvinciaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProveedorService {

	@Autowired
	ProveedorRepository proveedorRepository;
	
	@Autowired
	ProvinciaService provinciaService;
	
	@Autowired
	IvaService ivaService;
	
	@Autowired
	RubroService rubroService;
	
	public List<Proveedor> obtenerProveedores(){
		try {
	        return proveedorRepository.findByEstadoTrue();
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la lista de proveedores.", e);
	    }
	}
	
	public Proveedor obtenerProveedor(Integer id) throws ProveedorNotFoundException{
	    try {
	        return proveedorRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("El proveedor con el ID " + id + " no fue encontrado."));
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener el proveedor con el ID " + id, e);
	    }
	}
	
	public Proveedor agregarProveedor(Proveedor proveedor) {
		try {
	        Provincia provincia = provinciaService.obtenerProvincia(proveedor.getProvincia().getId());
	        Iva iva = ivaService.obtenerIva(proveedor.getIva().getId());
	        Rubro rubro = rubroService.obtenerRubro(proveedor.getRubro().getId());
	        proveedor.setEstado(true);
	        proveedor.setProvincia(provincia);
	        proveedor.setIva(iva);
	        proveedor.setRubro(rubro);
	        return proveedorRepository.save(proveedor);
	    } catch (ProvinciaNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (IvaNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (RubroNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public Proveedor borrarProveedor(Integer id) throws ProveedorNotFoundException{
		try {
			Proveedor proveedor = this.obtenerProveedor(id);
			proveedor.setEstado(false);
			return proveedorRepository.save(proveedor);
		} catch (ProveedorNotFoundException e) {
	        throw new RuntimeException(e.getMessage());
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
	
	public Proveedor modificarProveedor(Integer id, Proveedor proveedor) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		proveedor.setUpdated_at(time);
		if(proveedorRepository.findById(id).get() != null) {
			return proveedorRepository.save(proveedor);
		}
		return null;
	}
}
