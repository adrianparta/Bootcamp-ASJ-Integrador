package com.bootcamp.proyectointegrador.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.proyectointegrador.Models.Producto;
import com.bootcamp.proyectointegrador.Models.Proveedor;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	List<Producto> findByEstadoTrue();
	Boolean existsByCodigo(String codigo);
	List<Producto> findByProveedor(Proveedor proveedor);
}
