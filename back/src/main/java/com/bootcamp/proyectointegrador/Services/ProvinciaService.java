package com.bootcamp.proyectointegrador.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Models.Provincia;
import com.bootcamp.proyectointegrador.Repositories.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepository;
	
	public List<Provincia> obtenerProvincias(){
		return provinciaRepository.findAll();
	}
}
