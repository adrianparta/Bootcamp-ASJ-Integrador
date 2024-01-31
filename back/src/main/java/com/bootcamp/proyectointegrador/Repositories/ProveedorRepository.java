package com.bootcamp.proyectointegrador.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.proyectointegrador.Models.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
	Boolean existsByCodigo(String razonSocial);
	Boolean existsByCuit(String cuit);
	List<Proveedor> findByEstado(Boolean estado);
}
