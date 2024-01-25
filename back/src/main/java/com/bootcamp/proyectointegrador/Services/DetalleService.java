package com.bootcamp.proyectointegrador.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Models.Detalle;
import com.bootcamp.proyectointegrador.Repositories.DetalleRepository;

@Service
public class DetalleService {

	@Autowired
	DetalleRepository detalleRepository;
	
	public List<Detalle> obtenerDetalles(){
		return detalleRepository.findAll();
	}
	
	public Detalle obtenerDetalle(Integer id) {
		return detalleRepository.findById(id).get();
	}
	
	public Detalle agregarDetalle(Detalle detalle) {
		return detalleRepository.save(detalle);
	}
	

}
