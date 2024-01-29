package com.bootcamp.proyectointegrador.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.proyectointegrador.Models.Rubro;

public interface RubroRepository extends JpaRepository<Rubro, Integer> {
	Optional<Rubro> findByRubro(String rubro);
	List<Rubro> findByEstadoTrue();
}
