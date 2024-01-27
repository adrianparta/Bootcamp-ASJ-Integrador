package com.bootcamp.proyectointegrador.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Exceptions.RubroNotFoundException;
import com.bootcamp.proyectointegrador.Models.Categoria;
import com.bootcamp.proyectointegrador.Models.Rubro;
import com.bootcamp.proyectointegrador.Repositories.CategoriaRepository;
import com.bootcamp.proyectointegrador.Repositories.RubroRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RubroService {

	@Autowired
	RubroRepository rubroRepository;
	
	public List<Rubro> obtenerRubros(){
		return rubroRepository.findAll();
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
	
	public Object crearRubro(Rubro rubro) {
		rubro.setEstado(true);
		Optional<Rubro> rubroEncontrado = rubroRepository.findByRubro(rubro.getRubro());
		if(rubroEncontrado.isPresent()) {
			if(rubroEncontrado.get().getEstado() == true) {
				Map<String, String> error = new HashMap<>();
	        	error.put("categoria", "nombre de categoría repetido");
	        	return error;
			}
			else {
				rubro.setEstado(true);
				rubro.setId(rubroEncontrado.get().getId());
				return rubroRepository.save(rubro);
			}
		}
		return rubroRepository.save(rubro);
	}
	
	public Rubro borrarRubro(Integer id) {
		Rubro rubro = rubroRepository.findById(id).get();
		rubro.setEstado(false);
		rubroRepository.save(rubro);
		return rubro;
	}
}
