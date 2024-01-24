package com.bootcamp.proyectointegrador.Models;

import java.sql.Timestamp;

import java.sql.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@NotBlank(message = "El codigo no puede estar vacío")
    @Pattern(regexp = "^[A-Za-z]{2}[0-9]{3}$", message = "El formato del código debe ser: 2 letras seguidas de 3 números")
	private String codigo;
	
	@NotBlank(message = "El codigo no puede estar vacío")
	private String nombre;
	
	@NotBlank(message = "La descripción no puede estar vacía")
	private String descripcion;
	
	@NotBlank(message = "El precio debe ser mayor a 0")
	private Double precio;
	
	@Column(nullable = true)
	private String imagen_url;
	
	@NotBlank(message = "El precio no puede estar vacío")
	private Boolean estado;
	
	@NotBlank(message = "created_at vacío")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp created_at;
	
	@NotBlank(message = "updated_at vacío")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp updated_at;
	
	@NotBlank(message = "Debe seleccionar una categoría")
	private Categoria categoria_id;
	
	@NotBlank(message = "Debe seleccionar un proveedor")
	private Proveedor proveedor_id;

	public Producto(Integer id,
			@NotBlank(message = "El codigo no puede estar vacío") @Pattern(regexp = "^[A-Za-z]{2}[0-9]{3}$", message = "El formato del código debe ser: 2 letras seguidas de 3 números") String codigo,
			@NotBlank(message = "El codigo no puede estar vacío") String nombre,
			@NotBlank(message = "La descripción no puede estar vacía") String descripcion,
			@NotBlank(message = "El precio debe ser mayor a 0") Double precio, String imagen_url,
			@NotBlank(message = "El precio no puede estar vacío") Boolean estado,
			@NotBlank(message = "created_at vacío") Timestamp created_at,
			@NotBlank(message = "updated_at vacío") Timestamp updated_at,
			@NotBlank(message = "Debe seleccionar una categoría") Categoria categoria_id,
			@NotBlank(message = "Debe seleccionar un proveedor") Proveedor proveedor_id) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagen_url = imagen_url;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.categoria_id = categoria_id;
		this.proveedor_id = proveedor_id;
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

	public Categoria getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(Categoria categoria_id) {
		this.categoria_id = categoria_id;
	}

	public Proveedor getProveedor_id() {
		return proveedor_id;
	}

	public void setProveedor_id(Proveedor proveedor_id) {
		this.proveedor_id = proveedor_id;
	}

	public Integer getId() {
		return id;
	}
	
	
	
	
	
}
