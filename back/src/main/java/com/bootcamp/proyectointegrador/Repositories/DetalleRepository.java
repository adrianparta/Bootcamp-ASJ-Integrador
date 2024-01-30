package com.bootcamp.proyectointegrador.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.proyectointegrador.Models.Detalle;
import com.bootcamp.proyectointegrador.Models.Orden;

public interface DetalleRepository extends JpaRepository<Detalle, Integer>{

	List<Detalle> findByOrden(Orden orden);

}
