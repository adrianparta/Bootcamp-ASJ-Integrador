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
@Table(name = "Detalles")
public class Detalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "La cantidad no puede estar vacía")
	private Integer cantidad;
	
	@NotNull(message = "El precio no puede estar vacío")
	private Double precio_unitario;
	
	@NotNull(message = "La orden no puede estar vacía")
	private Orden ordenId;
	
	@NotNull(message = "El producto no puede estar vacío")
	private Producto productoId;

	public Detalle(Integer id, @NotNull(message = "La cantidad no puede estar vacía") Integer cantidad,
			@NotNull(message = "El precio no puede estar vacío") Double precio_unitario, Orden ordenId,
			@NotNull(message = "El producto no puede estar vacío") Producto productoId) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
		this.ordenId = ordenId;
		this.productoId = productoId;
	}

	public Detalle() {
		super();
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(Double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public Orden getOrdenId() {
		return ordenId;
	}

	public void setOrdenId(Orden ordenId) {
		this.ordenId = ordenId;
	}

	public Producto getProductoId() {
		return productoId;
	}

	public void setProductoId(Producto productoId) {
		this.productoId = productoId;
	}

	public Integer getId() {
		return id;
	}
	
	
	
}
