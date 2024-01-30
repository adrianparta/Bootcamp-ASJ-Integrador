package com.bootcamp.proyectointegrador.Models;

import com.bootcamp.proyectointegrador.DTOs.DetalleDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Detalles")
public class Detalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer cantidad;
	
	private Double precio_unitario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Orden orden;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Producto producto;

	public Detalle(Integer id, Integer cantidad, Double precio_unitario, Orden orden, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
		this.orden = orden;
		this.producto = producto;
	}

	public Detalle() {
		super();
	}

	public Detalle(DetalleDTO detalleDTO, Orden orden, Producto producto) {
		this.cantidad = detalleDTO.getCantidad();
		this.precio_unitario = detalleDTO.getPrecio_unitario();
		this.orden = orden;
		this.producto = producto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}	
	
	
}
