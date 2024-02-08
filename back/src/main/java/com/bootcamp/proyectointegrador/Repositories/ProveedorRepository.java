package com.bootcamp.proyectointegrador.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootcamp.proyectointegrador.Models.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
	Boolean existsByCodigo(String razonSocial);
	Boolean existsByCuit(String cuit);
	List<Proveedor> findByEstado(Boolean estado);
	
	@Query(value = "select top 1 p.* from proveedores p inner join (select p.id, count(o.id) as cantidad from proveedores p inner join ordenes o on o.proveedor_id = p.id group by p.id) aux on aux.id = p.id order by cantidad desc", nativeQuery = true)
	Proveedor proveedorMasVentas();
	
	@Query(value = "select top 1 cantidad from proveedores p inner join (select p.id, count(o.id) as cantidad from proveedores p inner join ordenes o on o.proveedor_id = p.id group by p.id) aux on aux.id = p.id order by cantidad desc", nativeQuery = true)
	Integer cantidadMasVentas();
}
