package com.bootcamp.proyectointegrador.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Models.Pais;
import com.bootcamp.proyectointegrador.Repositories.PaisRepository;

@Service
public class PaisService {

	@Autowired
	PaisRepository paisRepository;
	
	public List<Pais> obtenerPaises(){
		try {
	        return paisRepository.findAll();
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la lista de paises.", e);
	    }	
	}
}
