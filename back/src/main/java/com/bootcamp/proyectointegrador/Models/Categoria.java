package com.bootcamp.proyectointegrador.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El nombre de la categoría no puede estar vacío")
	private String categoria;
	
	private Boolean estado;

	public Categoria(Integer id, @NotBlank(message = "El nombre de la categoría no puede estar vacío") String categoria, Boolean estado) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.estado = true;
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
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	
}