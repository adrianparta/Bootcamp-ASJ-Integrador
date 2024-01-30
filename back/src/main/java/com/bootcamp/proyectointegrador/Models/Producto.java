package com.bootcamp.proyectointegrador.Models;

import java.sql.Timestamp;

import com.bootcamp.proyectointegrador.DTOs.ProductoDTO;

import java.sql.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String codigo;
	
	private String nombre;
	
	private String descripcion;
	
	private Double precio;
	
	@Column(nullable = true)
	private String imagen_url;
	
	private Boolean estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp updated_at;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Proveedor proveedor;

	public Producto(Integer id,
			@NotBlank(message = "El codigo no puede estar vacío") @Pattern(regexp = "^[A-Za-z]{2}[0-9]{3}$", message = "El formato del código debe ser: 2 letras seguidas de 3 números") String codigo,
			@NotBlank(message = "El codigo no puede estar vacío") String nombre,
			@NotBlank(message = "La descripción no puede estar vacía") String descripcion,
			@NotBlank(message = "El precio debe ser mayor a 0") Double precio, String imagen_url,
			@NotBlank(message = "Debe seleccionar una categoría") Categoria categoria,
			@NotBlank(message = "Debe seleccionar un proveedor") Proveedor proveedor) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagen_url = imagen_url;
		this.estado = true;
		this.created_at = new Timestamp(System.currentTimeMillis());
		this.updated_at = new Timestamp(System.currentTimeMillis());
		this.categoria = categoria;
		this.proveedor = proveedor;
	}

	public Producto(ProductoDTO productoDTO, Categoria categoria, Proveedor proveedor) {
		this.codigo = productoDTO.getCodigo();
		this.nombre = productoDTO.getNombre();
		this.descripcion = productoDTO.getDescripcion();
		this.precio = productoDTO.getPrecio();
		this.imagen_url = productoDTO.getImagen_url();
		this.estado = productoDTO.getEstado();
		this.created_at = new Timestamp(System.currentTimeMillis());
		this.updated_at = new Timestamp(System.currentTimeMillis());
		this.categoria = categoria;
		this.proveedor = proveedor;
	}
	
	public Producto() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getImagen_url() {
		return imagen_url;
	}

	public void setImagen_url(String imagen_url) {
		this.imagen_url = imagen_url;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	
}
