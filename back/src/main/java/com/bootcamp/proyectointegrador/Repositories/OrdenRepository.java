package com.bootcamp.proyectointegrador.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.proyectointegrador.Models.Orden;

public interface OrdenRepository extends JpaRepository<Orden, Integer>{
	List<Orden> findByEstado(Boolean estado);
}
