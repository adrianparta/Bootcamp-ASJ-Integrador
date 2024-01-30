package com.bootcamp.proyectointegrador.Models;

import java.sql.Timestamp;

import com.bootcamp.proyectointegrador.DTOs.OrdenDTO;

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

@Entity
@Table(name = "Ordenes")
public class Orden {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp fechaEmision;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp fechaEntrega;
	
	private String info;
	
	private Boolean estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp updated_at;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Proveedor proveedor;

	public Orden(Integer id, @NotNull(message = "La fecha de emision no puede estar vacía") Timestamp fechaEmision,
			@NotBlank(message = "La fecha de entrega no puede estar vacía") Timestamp fechaEntrega,
			@NotBlank(message = "La informacion de recepción no puede estar vacía") String info,
			@NotNull(message = "El estado no puede estar vacío") Boolean estado,
			@NotBlank(message = "created_at vacío") Timestamp created_at,
			@NotBlank(message = "updated_at vacío") Timestamp updated_at,
			@NotNull(message = "Debe seleccionar un proveedor") Proveedor proveedorId) {
		super();
		this.id = id;
		this.fechaEmision = fechaEmision;
		this.fechaEntrega = fechaEntrega;
		this.info = info;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.proveedor = proveedorId;
	}

	public Orden(OrdenDTO ordenDTO, Proveedor proveedor) {
		this.id = ordenDTO.getId();
		this.fechaEmision = ordenDTO.getFechaEmision();
		this.fechaEntrega = ordenDTO.getFechaEntrega();
		this.info = ordenDTO.getInfo();
		this.estado = ordenDTO.getEstado();
		this.created_at = new Timestamp(System.currentTimeMillis());
		this.updated_at = new Timestamp(System.currentTimeMillis());
		this.proveedor = proveedor;
	}
	
	public Orden() {
		super();
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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedorId) {
		this.proveedor = proveedorId;
	}

	public Integer getId() {
		return id;
	}
	
	
}
