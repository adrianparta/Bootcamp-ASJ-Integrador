package com.bootcamp.proyectointegrador.DTOs;

import java.sql.Timestamp;
import java.util.List;

import com.bootcamp.proyectointegrador.Models.Orden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrdenDTO {

	private Integer id;
	
	private Timestamp fechaEmision;
	
	@NotNull(message = "La fecha de entrega no puede estar vacía")
	private Timestamp fechaEntrega;
	
	@NotBlank(message = "La informacion de recepción no puede estar vacía")
	private String info;
	
	private Boolean estado;
	
	@NotNull(message = "Debe seleccionar un proveedor")
	private Integer proveedorId;
	
	private String proveedor;
	
	@NotNull(message = "la lista de detalles no puede estar vacía")
	private List<DetalleDTO> detalles; 

	public OrdenDTO(Integer id, Timestamp fechaEmision,
			@NotBlank(message = "La fecha de entrega no puede estar vacía") Timestamp fechaEntrega,
			@NotBlank(message = "La informacion de recepción no puede estar vacía") String info, Boolean estado,
			@NotNull(message = "Debe seleccionar un proveedor") Integer proveedorId) {
		super();
		this.id = id;
		this.fechaEmision = fechaEmision;
		this.fechaEntrega = fechaEntrega;
		this.info = info;
		this.estado = estado;
		this.proveedorId = proveedorId;
	}
	
	public OrdenDTO(Orden orden) {
		this.id = orden.getId();
		this.fechaEmision = orden.getFechaEmision();
		this.fechaEntrega = orden.getFechaEntrega();
		this.info = orden.getInfo();
		this.estado = orden.getEstado();
		this.proveedorId = orden.getProveedor().getId();
		this.proveedor = orden.getProveedor().getRazonSocial();
	}
	
	public OrdenDTO(Orden orden, List<DetalleDTO> detalles) {
		this.id = orden.getId();
		this.fechaEmision = orden.getFechaEmision();
		this.fechaEntrega = orden.getFechaEntrega();
		this.info = orden.getInfo();
		this.estado = orden.getEstado();
		this.proveedorId = orden.getProveedor().getId();
		this.proveedor = orden.getProveedor().getRazonSocial();
		this.detalles = detalles;
	}

	public OrdenDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Timestamp getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Timestamp fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(Integer proveedorId) {
		this.proveedorId = proveedorId;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public List<DetalleDTO> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleDTO> detalles) {
		this.detalles = detalles;
	}
	
	
}
