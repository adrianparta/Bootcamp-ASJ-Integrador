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
@Table(name = "Provincias")
public class Provincia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El nombre de la provincia no puede estar vacío")
	private String provincia;
	
	@NotNull(message = "El pais no puede estar vacío")
	private Provincia paisId;

	public Provincia(Integer id, @NotBlank(message = "El nombre de la provincia no puede estar vacío") String provincia,
			@NotNull(message = "El pais no puede estar vacío") Provincia paisId) {
		super();
		this.id = id;
		this.provincia = provincia;
		this.paisId = paisId;
	}

	public Provincia() {
		super();
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Provincia getPaisId() {
		return paisId;
	}

	public void setPaisId(Provincia paisId) {
		this.paisId = paisId;
	}

	public Integer getId() {
		return id;
	}
	
}
