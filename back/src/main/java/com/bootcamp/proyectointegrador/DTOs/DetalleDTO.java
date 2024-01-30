package com.bootcamp.proyectointegrador.DTOs;

import com.bootcamp.proyectointegrador.Models.Detalle;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DetalleDTO {

	
	private Integer id;
	
	@NotNull(message = "La cantidad no puede estar vacía")
	@Positive(message = "La cantidad debe ser mayor a 0")
	private Integer cantidad;
	
	private Double precio_unitario;
	
	private Integer ordenId;
	
	@NotNull(message = "El producto no puede estar vacío")
	private Integer productoId;
	
	private String producto;

	public DetalleDTO(Integer id,
			@NotNull(message = "La cantidad no puede estar vacía") @Positive(message = "La cantidad debe ser mayor a 0") Integer cantidad,
			@NotNull(message = "El precio no puede estar vacío") Double precio_unitario,
			@NotNull(message = "La orden no puede estar vacía") Integer ordenId,
			@NotNull(message = "El producto no puede estar vacío") Integer productoId, String producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
		this.ordenId = ordenId;
		this.productoId = productoId;
		this.producto = producto;
	}

	public DetalleDTO(Detalle detalle) {
		this.id = detalle.getId();
		this.cantidad = detalle.getCantidad();
		this.precio_unitario = detalle.getPrecio_unitario();
		this.ordenId = detalle.getOrden().getId();
		this.productoId = detalle.getProducto().getId();
		this.producto = detalle.getProducto().getNombre();
	}
	
	public DetalleDTO() {
		super();
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

	public Integer getOrdenId() {
		return ordenId;
	}

	public void setOrdenId(Integer ordenId) {
		this.ordenId = ordenId;
	}

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	
}
