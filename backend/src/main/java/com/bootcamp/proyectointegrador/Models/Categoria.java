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
@Table(name = "Categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	@NotBlank(message = "El nombre de la categoría no puede estar vacío")
	private String categoria;
	
	@NotNull(message = "El estado de la categoria no puede ser nulo")
	private Boolean estado;

	public Categoria(Integer id, @NotBlank(message = "El nombre de la categoría no puede estar vacío") String categoria,
			@NotNull(message = "El estado de la categoria no puede ser nulo") Boolean estado) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.estado = estado;
	}

	public Categoria() {
		super();
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	
}
