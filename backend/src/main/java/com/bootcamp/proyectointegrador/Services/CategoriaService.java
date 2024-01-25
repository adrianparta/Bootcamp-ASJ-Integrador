package com.bootcamp.proyectointegrador.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Models.Categoria;
import com.bootcamp.proyectointegrador.Repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Categoria> obtenerCategorias(){
		return categoriaRepository.findAll();
	}
	
	public Categoria crearCategoria(Categoria categoria) {
		
        for (Categoria categoriaAux : obtenerCategorias()) {
            if (categoriaAux.getCategoria().equals(categoria.getCategoria())) {
                categoriaAux.setEstado(true);
                return categoriaRepository.save(categoriaAux);
            }
        }
		
		return categoriaRepository.save(categoria);
	}
	
	public Categoria borrarCategoria(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).get();
		categoria.setEstado(false);
		categoriaRepository.save(categoria);
		return categoria;
	}
}
