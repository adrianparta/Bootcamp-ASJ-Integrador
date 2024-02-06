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
@Table(name = "Usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	@NotBlank(message = "El nombre de la condicion frente al IVA no puede estar vacío")
	private String usuario;
	
	@NotBlank(message = "La contraseña no puede estar vacía")
	private String contrasenia;

	public Usuario(Integer id,
			@NotBlank(message = "El nombre de la condicion frente al IVA no puede estar vacío") String usuario,
			@NotBlank(message = "La contraseña no puede estar vacía") String contrasenia) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}

	public Usuario() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
}
