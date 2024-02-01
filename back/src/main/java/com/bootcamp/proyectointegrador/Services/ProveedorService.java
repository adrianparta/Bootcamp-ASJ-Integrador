package com.bootcamp.proyectointegrador.Services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.DTOs.ProveedorDTO;
import com.bootcamp.proyectointegrador.Exceptions.IvaNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProveedorNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProvinciaNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.RubroNotFoundException;
import com.bootcamp.proyectointegrador.Models.Iva;
import com.bootcamp.proyectointegrador.Models.Proveedor;
import com.bootcamp.proyectointegrador.Models.Provincia;
import com.bootcamp.proyectointegrador.Models.Rubro;
import com.bootcamp.proyectointegrador.Repositories.ProveedorRepository;
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
	
	@Autowired
	ProductoService productoService;
	
	public List<ProveedorDTO> obtenerProveedores() {
		try {
	        List<Proveedor> proveedores =  proveedorRepository.findAll();
	        List<ProveedorDTO> proveedoresDTO = new ArrayList<ProveedorDTO>();
	        for (Proveedor proveedor : proveedores) {
				ProveedorDTO proveedorDTO = new ProveedorDTO(proveedor);
				proveedoresDTO.add(proveedorDTO);
			}
	        return proveedoresDTO;
	        
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la lista de proveedores.", e);
	    }
	}
	
	public List<ProveedorDTO> obtenerProveedoresPorEstado(Boolean estado){
		try {
	        List<Proveedor> proveedores =  proveedorRepository.findByEstado(estado);
	        List<ProveedorDTO> proveedoresDTO = new ArrayList<ProveedorDTO>();
	        for (Proveedor proveedor : proveedores) {
				ProveedorDTO proveedorDTO = new ProveedorDTO(proveedor);
				proveedoresDTO.add(proveedorDTO);
			}
	        return proveedoresDTO;
	        
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
	
	public ProveedorDTO obtenerProveedorDTO(Integer id) throws ProveedorNotFoundException{
	    try {
	        Proveedor proveedor = this.obtenerProveedor(id);
	    	ProveedorDTO proveedorDTO = new ProveedorDTO(proveedor);
	        return proveedorDTO;
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener el proveedor con el ID " + id, e);
	    }
	}
	
	public ProveedorDTO agregarProveedor(ProveedorDTO proveedorDTO) {
		try {
			if(proveedorRepository.existsByCodigo(proveedorDTO.getCodigo())) {
				throw new RuntimeException("El codigo ya está en uso");
			}
			if(proveedorRepository.existsByCuit(proveedorDTO.getCuit())) {
				throw new RuntimeException("El cuit ya está en uso");
			}
	        Provincia provincia = provinciaService.obtenerProvincia(proveedorDTO.getProvinciaId());
	        Iva iva = ivaService.obtenerIva(proveedorDTO.getIvaId());
	        Rubro rubro = rubroService.obtenerRubro(proveedorDTO.getRubroId());
	        if(!rubro.getEstado()) {
	        	throw new RuntimeException("El rubro que intenta seleccionar está deshabilitado");
	        }
	        
	        Proveedor proveedor = new Proveedor(proveedorDTO, provincia, rubro, iva);
	        proveedor.setEstado(true);
	        proveedorRepository.save(proveedor);
	        
	        proveedorDTO.setId(proveedor.getId());
	        proveedorDTO.setProvincia(proveedor.getProvincia().getProvincia());
	        proveedorDTO.setPais(proveedor.getProvincia().getPais().getPais());
	        proveedorDTO.setEstado(true);
	        return proveedorDTO;
	    } catch (ProvinciaNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (IvaNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (RubroNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}
	
	public ProveedorDTO modificarEstadoProveedor(Integer id) throws ProveedorNotFoundException{
		try {
			Proveedor proveedor = this.obtenerProveedor(id);
			proveedor.setEstado(!proveedor.getEstado());
			proveedorRepository.save(proveedor);
			ProveedorDTO proveedorDTO = new ProveedorDTO(proveedor);
			return proveedorDTO;
		} catch (ProveedorNotFoundException e) {
	        throw new RuntimeException(e.getMessage());
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
	
	public ProveedorDTO modificarProveedor(Integer id, ProveedorDTO proveedor){
		try {
			Proveedor proveedorModificado = this.obtenerProveedor(id);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			proveedorModificado.setUpdated_at(time);
			proveedorModificado.setRazonSocial(proveedor.getRazonSocial());
			proveedorModificado.setWeb(proveedor.getWeb());
			proveedorModificado.setEmail(proveedor.getEmail());
			proveedorModificado.setTelefono(proveedor.getTelefono());
			proveedorModificado.setCalle(proveedor.getCalle());
			proveedorModificado.setAltura(proveedor.getAltura());
			proveedorModificado.setCodigoPostal(proveedor.getCodigoPostal());
			proveedorModificado.setContactoNombre(proveedor.getContactoNombre());
			proveedorModificado.setContactoApellido(proveedor.getContactoApellido());
			proveedorModificado.setContactoTelefono(proveedor.getContactoTelefono());
			proveedorModificado.setContactoEmail(proveedor.getContactoEmail());
			proveedorModificado.setContactoRol(proveedor.getContactoRol());
			proveedorModificado.setLocalidad(proveedor.getLocalidad());
			proveedorModificado.setUrlImagen(proveedor.getUrlImagen());
			Provincia provincia = provinciaService.obtenerProvincia(proveedor.getProvinciaId());
	        Iva iva = ivaService.obtenerIva(proveedor.getIvaId());
	        Rubro rubro = rubroService.obtenerRubro(proveedor.getRubroId());
	        if(!rubro.getEstado() && rubro.getId() != proveedorModificado.getRubro().getId()) {
	            throw new RuntimeException("El rubro que intenta seleccionar está deshabilitado");
	        }
	        proveedorModificado.setEstado(proveedor.getEstado());
	        proveedorModificado.setProvincia(provincia);
	        proveedorModificado.setIva(iva);
	        proveedorModificado.setRubro(rubro);
			proveedorRepository.save(proveedorModificado);
			
			ProveedorDTO proveedorDTO = new ProveedorDTO(proveedorModificado);
			return proveedorDTO;
		} catch (ProveedorNotFoundException e) {
	        throw new RuntimeException(e.getMessage());
		} catch (ProvinciaNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (IvaNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (RubroNotFoundException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}

}
