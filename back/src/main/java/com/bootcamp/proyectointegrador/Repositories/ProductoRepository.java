package com.bootcamp.proyectointegrador.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.proyectointegrador.Models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
