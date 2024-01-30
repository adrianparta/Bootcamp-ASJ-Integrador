package com.bootcamp.proyectointegrador.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.proyectointegrador.Models.Pais;
import com.bootcamp.proyectointegrador.Models.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer>{
	List<Provincia> findByPais(Pais pais);
}
