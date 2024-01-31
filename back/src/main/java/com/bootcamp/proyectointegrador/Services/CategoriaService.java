package com.bootcamp.proyectointegrador.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.proyectointegrador.Exceptions.CategoriaNotFoundException;
import com.bootcamp.proyectointegrador.Models.Categoria;
import com.bootcamp.proyectointegrador.Repositories.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Categoria> obtenerCategorias(){
		try {
	        return categoriaRepository.findByEstadoTrue();
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la lista de categorías.", e);
	    }		
	}
	
	public Categoria obtenerCategoria(Integer id) throws CategoriaNotFoundException{
		try {
	        return categoriaRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("La categoría con el ID " + id + " no fue encontrada."));
	    } catch (Exception e) {
	        throw new RuntimeException("Error al intentar obtener la categoría con el ID " + id, e);
	    }	
	}
	
	public Categoria crearCategoria(Categoria categoria) {
		try {
			categoria.setEstado(true);
			Optional<Categoria> categoriaEncontrada = categoriaRepository.findByCategoria(categoria.getCategoria());
			if(categoriaEncontrada.isPresent()) {
				if(categoriaEncontrada.get().getEstado()) {
					throw new RuntimeException("El nombre ya está en uso");
				}
				else {
					categoriaEncontrada.get().setEstado(true);
					return categoriaRepository.save(categoriaEncontrada.get());
				}
			}
			return categoriaRepository.save(categoria);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}
	
	public Categoria modificarCategoria(Integer id, Categoria categoria) {
		try {
			Categoria categoriaModificada = this.obtenerCategoria(id);
			if(categoriaRepository.existsByCategoria(categoria.getCategoria()) && !categoriaModificada.getCategoria().equals(categoria.getCategoria())) {
				throw new RuntimeException("El nombre de categoría ya está en uso");
			}
			categoriaModificada.setCategoria(categoria.getCategoria());
			categoriaModificada.setEstado(categoria.getEstado());
			return categoriaRepository.save(categoriaModificada);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
}
