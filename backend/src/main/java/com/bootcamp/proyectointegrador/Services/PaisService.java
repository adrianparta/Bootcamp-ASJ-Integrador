package com.bootcamp.proyectointegrador.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Models.Pais;
import com.bootcamp.proyectointegrador.Models.Provincia;
import com.bootcamp.proyectointegrador.Repositories.PaisRepository;
import com.bootcamp.proyectointegrador.Repositories.ProvinciaRepository;

@Service
public class PaisService {

	@Autowired
	PaisRepository paisRepository;
	
	public List<Pais> obtenerPaises(){
		return paisRepository.findAll();
	}
}
