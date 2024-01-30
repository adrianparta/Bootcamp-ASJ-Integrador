package com.bootcamp.proyectointegrador.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Exceptions.ProvinciaNotFoundException;
import com.bootcamp.proyectointegrador.Models.Pais;
import com.bootcamp.proyectointegrador.Models.Provincia;
import com.bootcamp.proyectointegrador.Repositories.PaisRepository;
import com.bootcamp.proyectointegrador.Repositories.ProvinciaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepository;
	
	@Autowired
	PaisRepository paisRepository;
	
	public List<Provincia> obtenerProvincias(){
		try {
	        return provinciaRepository.findAll();
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la lista de provincias.", e);
	    }	
	}
	
	public Provincia obtenerProvincia(Integer id) throws ProvinciaNotFoundException {
	    try {
	        return provinciaRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("La provincia con el ID " + id + " no fue encontrada."));
	    } catch (EntityNotFoundException e) {
	        throw new ProvinciaNotFoundException("La provincia con el ID " + id + " no fue encontrada.", e);
	    } catch (Exception e) {
	        // Manejo de otras excepciones si es necesario
	        throw new RuntimeException("Error al intentar obtener la provincia con el ID " + id, e);
	    }
	}
	
	public List<Provincia> obtenerProvinciasPorPais(Integer id){
		try {
			Pais pais = paisRepository.findById(id).get();
	        return provinciaRepository.findByPais(pais);
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la lista de provincias.", e);
	    }	
	}
}
