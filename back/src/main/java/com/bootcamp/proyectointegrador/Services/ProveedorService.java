package com.bootcamp.proyectointegrador.Services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Exceptions.IvaNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProveedorNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.ProvinciaNotFoundException;
import com.bootcamp.proyectointegrador.Exceptions.RubroNotFoundException;
import com.bootcamp.proyectointegrador.Models.Iva;
import com.bootcamp.proyectointegrador.Models.Producto;
import com.bootcamp.proyectointegrador.Models.Proveedor;
import com.bootcamp.proyectointegrador.Models.Provincia;
import com.bootcamp.proyectointegrador.Models.Rubro;
import com.bootcamp.proyectointegrador.Repositories.ProductoRepository;
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
	
	@Autowired
	ProductoRepository productoRepository;
	
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
			if(proveedorRepository.existsByCodigo(proveedor.getCodigo())) {
				throw new RuntimeException("El codigo ya está en uso");
			}
			if(proveedorRepository.existsByCuit(proveedor.getCuit())) {
				throw new RuntimeException("El cuit ya está en uso");
			}
	        Provincia provincia = provinciaService.obtenerProvincia(proveedor.getProvincia().getId());
	        Iva iva = ivaService.obtenerIva(proveedor.getIva().getId());
	        Rubro rubro = rubroService.obtenerRubro(proveedor.getRubro().getId());
	        if(!rubro.getEstado()) {
	        	throw new RuntimeException("El rubro que intenta seleccionar está deshabilitado");
	        }
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
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}
	
	public Proveedor borrarProveedor(Integer id) throws ProveedorNotFoundException{
		try {
			Proveedor proveedor = this.obtenerProveedor(id);
			proveedor.setEstado(false);
			List<Producto> productos = productoRepository.findByProveedor(proveedor);
			for (Producto producto : productos) {
				productoRepository.delete(producto);
			}
			return proveedorRepository.save(proveedor);
		} catch (ProveedorNotFoundException e) {
	        throw new RuntimeException(e.getMessage());
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
	
	public Proveedor modificarProveedor(Integer id, Proveedor proveedor){
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
			Provincia provincia = provinciaService.obtenerProvincia(proveedor.getProvincia().getId());
	        Iva iva = ivaService.obtenerIva(proveedor.getIva().getId());
	        Rubro rubro = rubroService.obtenerRubro(proveedor.getRubro().getId());
	        if(!rubro.getEstado() && rubro.getId() != proveedorModificado.getRubro().getId()) {
	            throw new RuntimeException("El rubro que intenta seleccionar está deshabilitado");
	        }
	        proveedorModificado.setEstado(proveedor.getEstado());
	        proveedorModificado.setProvincia(provincia);
	        proveedorModificado.setIva(iva);
	        proveedorModificado.setRubro(rubro);
			return proveedorRepository.save(proveedorModificado);
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
