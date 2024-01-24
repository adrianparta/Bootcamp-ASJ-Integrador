package com.bootcamp.proyectointegrador.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Models.Orden;
import com.bootcamp.proyectointegrador.Repositories.OrdenRepository;

@Service
public class OrdenService {

	@Autowired
	OrdenRepository ordenRepository;
	
	public List<Orden> obtenerOrdenes(){
		return ordenRepository.findAll();
	}
	
	public Orden obtenerOrden(Integer id) {
		return ordenRepository.findById(id).get();
	}
	
	public Orden agregarOrden(Orden orden) {
		return ordenRepository.save(orden);
	}
	
	public Orden borrarOrden(Integer id) {
		Orden orden = ordenRepository.findById(id).get();
		orden.setEstado(false);
		ordenRepository.save(orden);
		return orden;  
	}
}