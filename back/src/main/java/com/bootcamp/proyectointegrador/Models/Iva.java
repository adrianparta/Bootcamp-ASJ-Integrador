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
public class Iva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	@NotBlank(message = "El nombre de la condicion frente al IVA no puede estar vacío")
	private String iva;

	public Iva(Integer id,
			@NotBlank(message = "El nombre de la condicion frente al IVA no puede estar vacío") String iva) {
		super();
		this.id = id;
		this.iva = iva;
	}

	public Iva() {
		super();
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public Integer getId() {
		return id;
	}
	
	
}
