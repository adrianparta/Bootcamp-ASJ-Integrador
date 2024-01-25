package com.bootcamp.proyectointegrador.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

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
	@ManyToOne(fetch = FetchType.EAGER)
	private Orden orden;
	
	@NotNull(message = "El producto no puede estar vacío")
	@ManyToOne(fetch = FetchType.EAGER)
	private Producto producto;

	public Detalle(Integer id, @NotNull(message = "La cantidad no puede estar vacía") Integer cantidad,
			@NotNull(message = "El precio no puede estar vacío") Double precio_unitario, Orden ordenId,
			@NotNull(message = "El producto no puede estar vacío") Producto productoId) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
		this.orden = ordenId;
		this.producto = productoId;
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
		return orden;
	}

	public void setOrdenId(Orden ordenId) {
		this.orden = ordenId;
	}

	public Producto getProductoId() {
		return producto;
	}

	public void setProductoId(Producto productoId) {
		this.producto = productoId;
	}

	public Integer getId() {
		return id;
	}
	
	
	
}
