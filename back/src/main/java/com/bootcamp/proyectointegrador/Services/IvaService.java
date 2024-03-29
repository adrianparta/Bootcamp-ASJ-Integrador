package com.bootcamp.proyectointegrador.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Exceptions.IvaNotFoundException;
import com.bootcamp.proyectointegrador.Models.Iva;
import com.bootcamp.proyectointegrador.Repositories.IvaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IvaService {

	@Autowired
	IvaRepository ivaRepository;
	
	public List<Iva> obtenerIvas(){
		try {
	        return ivaRepository.findAll();
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la lista de ivas.", e);
	    }	
	}
	
	public Iva obtenerIva(Integer id) throws IvaNotFoundException {
	    try {
	        return ivaRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("La situación ante el IVA con el ID " + id + " no fue encontrada."));
	    } catch (EntityNotFoundException e) {
	        throw new IvaNotFoundException("La situación ante el IVA con el ID " + id + " no fue encontrada.", e);
	    } catch (Exception e) {
	        // Manejo de otras excepciones si es necesario
	        throw new RuntimeException("Error al intentar obtener la situación ante el IVA con el ID " + id, e);
	    }
	}
	
}
