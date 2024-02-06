package com.bootcamp.proyectointegrador.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.proyectointegrador.Models.Iva;
import com.bootcamp.proyectointegrador.Models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Boolean existsByUsuarioAndContrasenia(String user, String pass);

}
