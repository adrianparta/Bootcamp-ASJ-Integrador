package com.bootcamp.proyectointegrador.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Models.Categoria;
import com.bootcamp.proyectointegrador.Repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Categoria> obtenerCategorias(){
		return categoriaRepository.findByEstadoTrue();
	}
	
	public Object crearCategoria(Categoria categoria) {
		categoria.setEstado(true);
		Optional<Categoria> categoriaEncontrada = categoriaRepository.findByCategoria(categoria.getCategoria());
		if(categoriaEncontrada.isPresent()) {
			if(categoriaEncontrada.get().getEstado() == true) {
				Map<String, String> error = new HashMap<>();
	        	error.put("categoria", "nombre de categoría repetido");
	        	return error;
			}
			else {
				categoria.setEstado(true);
				categoria.setId(categoriaEncontrada.get().getId());
				return categoriaRepository.save(categoria);
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
