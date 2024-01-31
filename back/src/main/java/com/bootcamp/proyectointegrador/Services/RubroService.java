package com.bootcamp.proyectointegrador.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Exceptions.RubroNotFoundException;
import com.bootcamp.proyectointegrador.Models.Rubro;
import com.bootcamp.proyectointegrador.Repositories.RubroRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RubroService {

	@Autowired
	RubroRepository rubroRepository;
	
	public List<Rubro> obtenerRubros(){
		try {
	        return rubroRepository.findByEstadoTrue();
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la lista de rubros.", e);
	    }	
	}
	
	public Rubro obtenerRubro(Integer id) throws RubroNotFoundException {
	    try {
	        return rubroRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("El rubro con el ID " + id + " no fue encontrado."));
	    } catch (EntityNotFoundException e) {
	        throw new RubroNotFoundException("El rubro con el ID " + id + " no fue encontrado.", e);
	    } catch (Exception e) {
	        // Manejo de otras excepciones si es necesario
	        throw new RuntimeException("Error al intentar obtener el rubro con el ID " + id, e);
	    }
	}
	
	public Rubro crearRubro(Rubro rubro) {
		try {
			rubro.setEstado(true);
			Optional<Rubro> rubroEncontrado = rubroRepository.findByRubro(rubro.getRubro());
			if(rubroEncontrado.isPresent()) {
				if(rubroEncontrado.get().getEstado()) {
					throw new RuntimeException("El nombre ya está en uso");
				}
				else {
					rubroEncontrado.get().setEstado(true);
					return rubroRepository.save(rubroEncontrado.get());
				}
			}
			return rubroRepository.save(rubro);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}
	
	public Rubro modificarRubro(Integer id, Rubro rubro) {
		try {
			Rubro rubroModificado = this.obtenerRubro(id);
			if(rubroRepository.existsByRubro(rubro.getRubro()) && !rubroModificado.getRubro().equals(rubro.getRubro())) {
				throw new RuntimeException("El nombre de rubro ya está en uso ya está en uso");
			}
			rubroModificado.setRubro(rubro.getRubro());
			rubroModificado.setEstado(rubro.getEstado());
			return rubroRepository.save(rubroModificado);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
}
