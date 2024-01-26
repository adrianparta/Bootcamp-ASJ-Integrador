package com.bootcamp.proyectointegrador.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Rubros")
public class Rubro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	@NotBlank(message = "El nombre del rubro no puede estar vacío")
	private String rubro;
	
	@NotNull(message = "El estado del rubro no puede estar vacío")
	private Boolean estado;

	public Rubro(Integer id, @NotBlank(message = "El nombre del rubro no puede estar vacío") String rubro) {
		super();
		this.id = id;
		this.rubro = rubro;
		this.estado = true;
	}

	public Rubro() {
		super();
		this.estado= true;	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	
}
