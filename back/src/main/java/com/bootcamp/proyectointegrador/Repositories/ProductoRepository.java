package com.bootcamp.proyectointegrador.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.proyectointegrador.Models.Categoria;
import com.bootcamp.proyectointegrador.Models.Producto;
import com.bootcamp.proyectointegrador.Models.Proveedor;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	Boolean existsByCodigo(String codigo);
	List<Producto> findByProveedorAndEstado(Proveedor proveedor, Boolean estado);
	List<Producto> findByCategoriaAndEstado(Categoria categoria, Boolean estado);
	List<Producto> findByEstado(Boolean estado);
}
