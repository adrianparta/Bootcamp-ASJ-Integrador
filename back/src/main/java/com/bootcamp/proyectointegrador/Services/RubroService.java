package com.bootcamp.proyectointegrador.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Models.Categoria;
import com.bootcamp.proyectointegrador.Models.Rubro;
import com.bootcamp.proyectointegrador.Repositories.CategoriaRepository;
import com.bootcamp.proyectointegrador.Repositories.RubroRepository;

@Service
public class RubroService {

	@Autowired
	RubroRepository rubroRepository;
	
	public List<Rubro> obtenerRubros(){
		return rubroRepository.findAll();
	}
	
	public Rubro crearRubro(Rubro rubro) {
		
        for (Rubro rubroAux : obtenerRubros()) {
            if (rubroAux.getRubro().equals(rubro.getRubro())) {
                rubroAux.setEstado(true);
                return rubroRepository.save(rubroAux);
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
