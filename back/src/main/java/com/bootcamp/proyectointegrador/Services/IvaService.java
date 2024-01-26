package com.bootcamp.proyectointegrador.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Models.Categoria;
import com.bootcamp.proyectointegrador.Models.Iva;
import com.bootcamp.proyectointegrador.Repositories.CategoriaRepository;
import com.bootcamp.proyectointegrador.Repositories.IvaRepository;

@Service
public class IvaService {

	@Autowired
	IvaRepository ivaRepository;
	
	public List<Iva> obtenerIvas(){
		return ivaRepository.findAll();
	}
	
	public Iva obtenerIva(Integer id) {
		return ivaRepository.findById(id).get();
	}
	
}
