package com.bootcamp.proyectointegrador.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.proyectointegrador.Models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
