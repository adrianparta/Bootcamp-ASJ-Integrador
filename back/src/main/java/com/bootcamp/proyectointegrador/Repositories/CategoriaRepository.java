package com.bootcamp.proyectointegrador.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.proyectointegrador.Models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	List<Categoria>findByEstadoTrue();
	Optional<Categoria> findByCategoria(String categoria);
}
