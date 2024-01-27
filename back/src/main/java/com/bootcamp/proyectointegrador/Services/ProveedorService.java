package com.bootcamp.proyectointegrador.Services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Exceptions.ProvinciaNotFoundException;
import com.bootcamp.proyectointegrador.Models.Proveedor;
import com.bootcamp.proyectointegrador.Models.Provincia;
import com.bootcamp.proyectointegrador.Repositories.ProveedorRepository;
import com.bootcamp.proyectointegrador.Repositories.ProvinciaRepository;

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
		return proveedorRepository.findByEstadoTrue();
	}
	
	public Proveedor obtenerProveedor(Integer id) {
		return proveedorRepository.findById(id).get();
	}
	
	public Proveedor agregarProveedor(Proveedor proveedor) {
		try {
	        Provincia provincia = provinciaService.obtenerProvincia(proveedor.getProvincia().getId());
	        proveedor.setProvincia(provincia);
	        proveedor.setEstado(true);
	        proveedor.setIva(ivaService.obtenerIva(proveedor.getIva().getId()));
	        proveedor.setRubro(rubroService.obtenerRubro(proveedor.getRubro().getId()));
	        return proveedorRepository.save(proveedor);
	    } catch (ProvinciaNotFoundException e) {
	        throw new RuntimeException("Error al agregar proveedor: " + e.getMessage(), e);
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar agregar proveedor", e);
	    }
	}
	
	public Proveedor borrarProveedor(Integer id) {
		Proveedor proveedor = proveedorRepository.findById(id).get();
		proveedor.setEstado(false);
		proveedorRepository.save(proveedor);
		return proveedor;
	}
	
	public Proveedor modificarProveedor(Integer id, Proveedor proveedor) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		proveedor.setUpdated_at(time);
		if(proveedorRepository.findById(id).get() != null) {
			return proveedorRepository.save(proveedor);
		}
		return null;
	}

	/* // CONSULTA: ALGUNO DE ESTOS METODOS ES MAS ADECUADO?
	public Proveedor modificarProveedor(Integer id, Proveedor proveedor) {
		Proveedor proveedorAux = proveedorRepository.findById(id).get();
		if(proveedorAux != null) {
			proveedorAux.setContactoNombre(proveedor.getContactoNombre());
			proveedorAux.setCalle(proveedor.getCalle());
			return proveedorRepository.save(proveedorAux);
		}
		return null;
	}
	
	
	public Proveedor modificarProveedor(Integer id, Proveedor proveedor) {
		proveedorRepository.save(proveedor);
	}
	*/
}
