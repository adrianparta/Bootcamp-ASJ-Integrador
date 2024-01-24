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

	public Rubro(Integer id, @NotBlank(message = "El nombre del rubro no puede estar vacío") String rubro) {
		super();
		this.id = id;
		this.rubro = rubro;
	}

	public Rubro() {
		super();
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public Integer getId() {
		return id;
	}
	
}
